package controller;

import dto.UserDto;
import entity.Battle;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.BattleService;
import service.CardService;
import service.FinishService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FinishControllerTest {
    private UserService userService = mock(UserService.class);
    private FinishService finishService = mock(FinishService.class);
    private BattleService battleService = mock(BattleService.class);
    private CardService cardService = mock(CardService.class);

    @Mock
    private User user;
    @Mock
    private UserDto userDto;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private Battle battle;

    @Before
    public void before() {
        when(request.getSession()).thenReturn(session);
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(userDto);
        when(battleService.getBattleById((Integer) request.getSession().getAttribute("battleId"))).thenReturn(battle);
        when(finishService.result(battleService.getBattleById((Integer) request.getSession()
                .getAttribute("battleId")))).thenReturn(battle);
        when(userService.getUserAttributeFromSession(session)).thenReturn(user);
    }

    @Test
    public void finishViewPlayer1Test() {
        when(battleService.isUserDtoPlayer1(userDto, battle)).thenReturn(true);
        FinishController finish = new FinishController(userService, battleService, finishService, cardService);
        ModelAndView result = finish.finishView(request, response);
        assertNotNull(result);
        assertEquals("finish", result.getViewName());
        assertEquals(1, result.getModel().size());
        verify(battleService, times(2)).getBattleById((Integer) request.getSession().getAttribute("battleId"));
        verify(finishService).result(battleService.getBattleById((Integer) request.getSession().getAttribute("battleId")));
        verify(userService).getUserAttributeFromSession(session);
        verify(finishService).updateUserPoints(userDto, user);
        verify(cardService).getUserCards(user.getDeck());
    }

    @Test
    public void finishViewPlayer2Test() {
        when(battleService.isUserDtoPlayer1(userDto, battle)).thenReturn(false);
        when(battleService.isUserDtoPlayer1(userDto, battleService.inverse(battle))).thenReturn(true);
        FinishController finish = new FinishController(userService, battleService, finishService, cardService);
        ModelAndView result = finish.finishView(request, response);
        assertNotNull(result);
        assertEquals("finish", result.getViewName());
        assertEquals(1, result.getModel().size());
        verify(battleService, times(2)).getBattleById((Integer) request.getSession().getAttribute("battleId"));
        verify(finishService).result(battleService.getBattleById((Integer) request.getSession().getAttribute("battleId")));
        verify(battleService, times(3)).inverse(battle);
        verify(userService).getUserAttributeFromSession(session);
        verify(finishService).updateUserPoints(userDto, user);
        verify(cardService).getUserCards(user.getDeck());
    }

    @Test
    public void finishViewNullUserTest() {
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(null);
        FinishController finish = new FinishController(userService, battleService, finishService, cardService);
        ModelAndView result = finish.finishView(request, response);
        assertNotNull(result);
        assertEquals("login", result.getViewName());
    }

    @Test
    public void finishFirstUserExitTest() throws IOException {
        when(finishService.isFirstExit()).thenReturn(false);
        FinishController finish = new FinishController(userService, battleService, finishService, cardService);
        finish.finish(response, request);
        verify(userService).getUserDtoAttributeFromSession(session);
        verify(finishService).setFirstExit();
        verify(request, times(5)).getSession();
        verify(request.getSession()).setAttribute(anyString(), anyInt());
        verify(response).sendRedirect(anyString());
    }

    @Test
    public void finishUserExitTest() throws IOException {
        when(finishService.isFirstExit()).thenReturn(true);
        FinishController finish = new FinishController(userService, battleService, finishService, cardService);
        finish.finish(response, request);
        verify(userService).getUserDtoAttributeFromSession(session);
        verify(finishService).notFirstExiting();
        verify(request.getSession(), times(4)).getAttribute(anyString());
        verify(battleService, times(2)).getBattleById((Integer) request.getSession().getAttribute("battleId"));
        verify(finishService).deleteActivePlayers(battle);
        verify(finishService).deleteBattle((Integer) request.getSession().getAttribute("battleId"));
        verify(request.getSession()).setAttribute("battleId", null);
        verify(response).sendRedirect(anyString());
    }

    @Test
    public void finishNullUserTest() throws IOException {
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(null);
        FinishController finish = new FinishController(userService, battleService, finishService, cardService);
        finish.finish(response, request);
        verify(response).sendRedirect(anyString());
    }

}

package controller;

import entity.Battle;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.BattleService;
import service.FinishService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FinishControllerTest {
    private UserService userService = mock(UserService.class);
    private FinishService finishService = mock(FinishService.class);
    private BattleService battleService = mock(BattleService.class);

    @Mock
    private User user;
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
        when(userService.getUserAttributeFromSession(request.getSession())).thenReturn(user);
        when(finishService.calculatePoints(battleService.getBattleById((Integer) request.getSession()
                .getAttribute("battleId")))).thenReturn(battle);
        when(user.getLogin()).thenReturn("any");
        when(battleService.isUserInBattle2("any")).thenReturn(5);
        when(battleService.getBattleById(5)).thenReturn(battle);
        when((battleService.getBattleById(battleService.isUserInBattle2(user.getLogin())).getPlayer1())).thenReturn(user);


    }

    @Test
    public void finishViewPlayer1Test() throws IOException {
//        when(user.equals((battleService.getBattleById(battleService.isUserInBattle2(user.getLogin())).getPlayer1()))).thenReturn(true);
        FinishController finish = new FinishController(userService, battleService, finishService);
        ModelAndView result = finish.finishView(request, response);
        assertNotNull(result);
        assertEquals("finish", result.getViewName());
        assertEquals(1, result.getModel().size());
        verify(battleService, times(2)).getBattleById((Integer) request.getSession().getAttribute("battleId"));
        verify(finishService, times(0)).calculatePoints((battleService.getBattleById((Integer) request.getSession().getAttribute("battleId"))));
        verify(battleService, times(0)).inverse(battle);

    }

    @Test
    public void finishViewPlayer2Test() throws IOException {
        when(user.equals(battleService.getBattleById(battleService.isUserInBattle2(user.getLogin())).getPlayer2())).thenReturn(true);
        FinishController finish = new FinishController(userService, battleService, finishService);
        ModelAndView result = finish.finishView(request, response);
        assertNotNull(result);
        assertEquals("finish", result.getViewName());
        assertEquals(1, result.getModel().size());
        verify(battleService).getBattleById((Integer) request.getSession().getAttribute("battleId"));
        verify(finishService).calculatePoints(battle);
        verify(battleService, times(1)).inverse(battle);
    }

    @Test
    public void finishViewNullUserTest() throws IOException {
        when(userService.getUserAttributeFromSession(request.getSession())).thenReturn(null);
        FinishController finish = new FinishController(userService, battleService, finishService);
        ModelAndView result = finish.finishView(request, response);
        assertNull(result);
        verify(response).sendRedirect("/fs/");
    }

}

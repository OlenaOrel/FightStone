package controller;

import collections.ActivePlayers;
import collections.UsersOnline;
import collections.WaitUsers;
import dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {
    private UserService userService = mock(UserService.class);
    private WaitUsers waitUsers = mock(WaitUsers.class);
    private UsersOnline usersOnline = mock(UsersOnline.class);
    private ActivePlayers activePlayers = mock(ActivePlayers.class);
    @Mock
    private UserDto userDto;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void before() {
        when(request.getSession()).thenReturn(session);
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(userDto);
    }

    @Test
    public void mainPageTest() throws IOException {
        MainController mainController = new MainController(userService, waitUsers, usersOnline, activePlayers);
        ModelAndView result = mainController.mainPage(response, request);
        assertNotNull(result);
        assertEquals(5, result.getModel().size());
        assertEquals("main", result.getViewName());
        assertTrue(result.getModel().containsKey("uDto"));
        assertTrue(result.getModel().containsKey("userCards"));
        assertTrue(result.getModel().containsKey("wait"));
        assertTrue(result.getModel().containsKey("online"));
        assertTrue(result.getModel().containsKey("active"));
    }

    @Test
    public void mainPageNullUserTest() throws IOException {
        when(userService.getUserDtoAttributeFromSession(request.getSession())).thenReturn(null);
        MainController mainController = new MainController(userService, waitUsers, usersOnline, activePlayers);
        ModelAndView result = mainController.mainPage(response, request);
        verify(response, times(1)).sendRedirect(anyString());
        assertNull(result);
    }

    @Test
    public void logOutTest() throws IOException {
        MainController mainController = new MainController(userService, waitUsers, usersOnline, activePlayers);
        String logout = eq(anyString());
        mainController.logOut(session, response, logout);
        verify(session, times(1)).setAttribute("user", null);
        verify(userService, times(1)).removeUserFromSession(logout);
        verify(response).sendRedirect("/fs/");
    }
}

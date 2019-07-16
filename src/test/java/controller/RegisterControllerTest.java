package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {
    private UserService userService = mock(UserService.class);

    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;

    @Before
    public void before() {
        when(userService.isUserExists(anyString())).thenReturn(false);
    }

    @Test
    public void registerViewTest() {
        RegisterController controller = new RegisterController(userService);
        ModelAndView result = controller.registerView();
        assertNotNull(result);
        assertEquals("register", result.getViewName());
    }


    @Test
    public void registerTestIncorrectConfirmPass() throws IOException {
        when(userService.isPassConfirm(anyString(), anyString())).thenReturn(false);
        RegisterController controller = new RegisterController(userService);
        controller.register("login", "pass", "cpass", resp, req);
        verify(userService, times(1)).isUserExists(anyString());
        verify(userService, times(1)).isPassConfirm(anyString(), anyString());
        verify(resp, times(1)).sendRedirect(anyString());
    }

    @Test
    public void registerTestUserExists() throws IOException {
        when(userService.isUserExists(anyString())).thenReturn(true);
        RegisterController controller = new RegisterController(userService);
        controller.register("login", "pass", "cpass", resp, req);
        verify(resp, times(1)).sendRedirect(anyString());
    }

    @Test
    public void registerTestUserNew() throws IOException {
        when(userService.isPassConfirm(anyString(), anyString())).thenReturn(true);
        RegisterController controller = new RegisterController(userService);
        controller.register("login", "pass", "cpass", resp, req);
        verify(userService, times(1)).isPassConfirm(anyString(), anyString());
        verify(userService, times(1)).save(anyString(), anyString());
        verify(userService, times(1)).getUserDto(any());
        verify(userService, times(1)).setUserAttributeToSession(any(), any(), any());
        verify(userService, times(1)).addUsersOnline(any());
        verify(resp, times(1)).sendRedirect(anyString());
    }


}

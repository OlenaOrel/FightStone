package service;

import dao.UserDao;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserDao udao = mock(UserDao.class);
    @Mock
    private User user;

    @Before
    public void before() {
        when(udao.getByLogin(anyString())).thenReturn(user);
    }

    @Test
    public void isUserExistsTestExists() {
        UserService service = new UserService(udao);
        boolean result = service.isUserExists("login");
        assertTrue(result);
    }

    @Test
    public void isUserExistsTestNotExists() {
        when(udao.getByLogin(anyString())).thenReturn(null);
        UserService service = new UserService(udao);
        boolean result = service.isUserExists("login");
        assertFalse(result);
    }

    @Test
    public void getByLoginTestExists() {
        UserService service = new UserService(udao);
        User result = service.getByLogin("login");
        assertNotNull(result);
    }

    @Test
    public void getByLoginTestNotExists() {
        when(udao.getByLogin(anyString())).thenReturn(null);
        UserService service = new UserService(udao);
        User result = service.getByLogin("login");
        assertNull(result);
    }

    @Test
    public void saveTest() {
        UserService service = new UserService(udao);
        service.save("login", "pass");
        verify(udao, times(1)).add(any());
    }
}

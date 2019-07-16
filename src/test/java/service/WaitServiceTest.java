package service;

import collections.WaitUsers;
import dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WaitServiceTest {

    private WaitUsers waitUsers = mock(WaitUsers.class);

    @Mock
    UserDto uDto;
    @Mock
    Map<String, UserDto> users;
    @Mock
    Set<String> logins;
    @Mock
    Iterator<String> iterator;

    @Before
    public void before() {
        when(waitUsers.getWaitList()).thenReturn(users);
        when(users.isEmpty()).thenReturn(false);
    }

    @Test
    public void isWaitListEmptyTestNotEmpty() {
        WaitService service = new WaitService(waitUsers);
        boolean result = service.isWaitListEmpty();
        assertFalse(result);
    }

    @Test
    public void isWaitListEmptyTestEmpty() {
        when(users.isEmpty()).thenReturn(true);
        WaitService service = new WaitService(waitUsers);
        boolean result = service.isWaitListEmpty();
        assertTrue(result);
    }

    @Test
    public void getUserForBattleTest() {
        when(waitUsers.getWaitList().keySet()).thenReturn(logins);
        when(waitUsers.getWaitList().keySet().iterator()).thenReturn(iterator);
        when(waitUsers.getWaitList().remove(waitUsers.getWaitList().keySet().iterator().next())).thenReturn(uDto);
        WaitService service = new WaitService(waitUsers);
        UserDto result = service.getUserForBattle();
        assertNotNull(result);
    }

    @Test
    public void waitUsersListContainsUserByLoginTestExist() {
        when(users.containsKey("login")).thenReturn(true);
        WaitService service = new WaitService(waitUsers);
        boolean result = service.waitUsersListContainsUserByLogin("login");
        assertTrue(result);
    }

    @Test
    public void waitUsersListContainsUserByLoginTestNotExist() {
        WaitService service = new WaitService(waitUsers);
        boolean result = service.waitUsersListContainsUserByLogin("login");
        assertFalse(result);
    }
}

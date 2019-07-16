package collections;

import dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UsersOnline {
    private Map<String, UserDto> usersOnline = new ConcurrentHashMap<>();

    public Map<String, UserDto> getUsersOnline() {
        return usersOnline;
    }
}

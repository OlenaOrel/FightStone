package collections;

import entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UsersOnline {
    private Map<String, User> usersOnline = new ConcurrentHashMap<>();

    public Map<String, User> getUsersOnline() {
        return usersOnline;
    }
}

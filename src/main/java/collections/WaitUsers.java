package collections;

import entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WaitUsers {
    private Map<String, User> waitList = new ConcurrentHashMap<>();

    public Map<String, User> getWaitList() {
        return waitList;
    }

    public void setWaitList(Map<String, User> waitList) {
        this.waitList = waitList;
    }
}

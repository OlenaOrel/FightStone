package collections;

import dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WaitUsers {
    private Map<String, UserDto> waitList = new ConcurrentHashMap<>();

    public Map<String, UserDto> getWaitList() {
        return waitList;
    }

    public void setWaitList(Map<String, UserDto> waitList) {
        this.waitList = waitList;
    }
}

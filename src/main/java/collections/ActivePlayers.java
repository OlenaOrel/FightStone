package collections;


import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ActivePlayers {
    private Map<String, Integer> activePlayersList = new ConcurrentHashMap<>();

    public Map<String, Integer> getActivePlayersList() {
        return activePlayersList;
    }

}

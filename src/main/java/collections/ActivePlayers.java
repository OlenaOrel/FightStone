package collections;

import entity.Battle;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ActivePlayers {
    private Map<String, Battle> activePlayersList = new ConcurrentHashMap<>();

    public Map<String, Battle> getActivePlayersList() {
        return activePlayersList;
    }

    public void setActivePlayersList(Map<String, Battle> activePlayersList) {
        this.activePlayersList = activePlayersList;
    }
}

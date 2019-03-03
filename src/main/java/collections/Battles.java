package collections;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Battles {
    private Map<Integer, Battle> battleList = new ConcurrentHashMap<>();

    public Map<Integer, Battle> getBattleList() {
        return battleList;
    }

    public void setBattleList(Map<Integer, Battle> battleList) {
        this.battleList = battleList;
    }
}

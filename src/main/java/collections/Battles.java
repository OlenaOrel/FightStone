package collections;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Battles {
    private Map<Integer, Battles> battleList = new ConcurrentHashMap<>();

    public Map<Integer, Battles> getBattleList() {
        return battleList;
    }

    public void setBattleList(Map<Integer, Battles> battleList) {
        this.battleList = battleList;
    }
}

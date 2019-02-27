package holder;

import java.util.LinkedList;
import java.util.List;

public class CardHolder {
    public List<Integer> cards = new LinkedList<>();

    public CardHolder(List<Integer> cards) {
        this.cards = cards;
    }

    public CardHolder() {
    }
}


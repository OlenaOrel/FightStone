package entity;

import java.util.Collections;
import java.util.List;

public class Battle {
    private int id;
    private List<Card> deck1;
    private List<Card> deck2;
    private List<Card> inHand1;
    private List<Card> inHand2;
    private List<Card> onTable1;
    private List<Card> onTable2;
    private int numberOfMove;
    private boolean isMove1;
    private int mana1;
    private int mana2;
    private int hp1;
    private int hp2;
    private boolean isHeroPowered1;
    private boolean isHeroPowered2;
    private boolean isFromHandChoosen1;
    private boolean isFromHandChoosen2;
    private boolean isFromTableChoosen1;
    private boolean isFromTableChoosen2;

    public Battle() {
    }

    public Battle(int id, List<Card> deck1, List<Card> deck2, List<Card> inHand1,
                  List<Card> inHand2, List<Card> onTable1, List<Card> onTable2,
                  int numberOfMove, boolean isMove1, int mana1, int mana2, int hp1, int hp2,
                  boolean isHeroPowered1, boolean isHeroPowered2, boolean isFromHandChoosen1,
                  boolean isFromHandChoosen2, boolean isFromTableChoosen1, boolean isFromTableChoosen2) {
        this.id = id;
        this.deck1 = deck1;
        this.deck2 = deck2;
        this.inHand1 = inHand1;
        this.inHand2 = inHand2;
        this.onTable1 = onTable1;
        this.onTable2 = onTable2;
        this.numberOfMove = numberOfMove;
        this.isMove1 = isMove1;
        this.mana1 = mana1;
        this.mana2 = mana2;
        this.hp1 = hp1;
        this.hp2 = hp2;
        this.isHeroPowered1 = isHeroPowered1;
        this.isHeroPowered2 = isHeroPowered2;
        this.isFromHandChoosen1 = isFromHandChoosen1;
        this.isFromHandChoosen2 = isFromHandChoosen2;
        this.isFromTableChoosen1 = isFromTableChoosen1;
        this.isFromTableChoosen2 = isFromTableChoosen2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Card> getDeck1() {
        Collections.shuffle(deck1);
        return deck1;
    }

    public void setDeck1(List<Card> deck1) {
        this.deck1 = deck1;
    }

    public List<Card> getDeck2() {
        Collections.shuffle(deck2);
        return deck2;
    }

    public void setDeck2(List<Card> deck2) {
        this.deck2 = deck2;
    }

    public List<Card> getInHand1() {
        return inHand1;
    }

    public void setInHand1(List<Card> inHand1) {
        this.inHand1 = inHand1;
    }

    public List<Card> getInHand2() {
        return inHand2;
    }

    public void setInHand2(List<Card> inHand2) {
        this.inHand2 = inHand2;
    }

    public List<Card> getOnTable1() {
        return onTable1;
    }

    public void setOnTable1(List<Card> onTable1) {
        this.onTable1 = onTable1;
    }

    public List<Card> getOnTable2() {
        return onTable2;
    }

    public void setOnTable2(List<Card> onTable2) {
        this.onTable2 = onTable2;
    }

    public int getNumberOfMove() {
        return numberOfMove;
    }

    public void setNumberOfMove(int numberOfMove) {
        this.numberOfMove = numberOfMove;
    }

    public boolean isMove1() {
        return isMove1;
    }

    public void setMove1(boolean move1) {
        isMove1 = move1;
    }

    public int getMana1() {
        return mana1;
    }

    public void setMana1(int mana1) {
        this.mana1 = mana1;
    }

    public int getMana2() {
        return mana2;
    }

    public void setMana2(int mana2) {
        this.mana2 = mana2;
    }

    public int getHp1() {
        return hp1;
    }

    public void setHp1(int hp1) {
        this.hp1 = hp1;
    }

    public int getHp2() {
        return hp2;
    }

    public void setHp2(int hp2) {
        this.hp2 = hp2;
    }

    public boolean isHeroPowered1() {
        return isHeroPowered1;
    }

    public void setHeroPowered1(boolean heroPowered1) {
        isHeroPowered1 = heroPowered1;
    }

    public boolean isHeroPowered2() {
        return isHeroPowered2;
    }

    public void setHeroPowered2(boolean heroPowered2) {
        isHeroPowered2 = heroPowered2;
    }

    public boolean isFromHandChoosen1() {
        return isFromHandChoosen1;
    }

    public void setFromHandChoosen1(boolean fromHandChoosen1) {
        isFromHandChoosen1 = fromHandChoosen1;
    }

    public boolean isFromHandChoosen2() {
        return isFromHandChoosen2;
    }

    public void setFromHandChoosen2(boolean fromHandChoosen2) {
        isFromHandChoosen2 = fromHandChoosen2;
    }

    public boolean isFromTableChoosen1() {
        return isFromTableChoosen1;
    }

    public void setFromTableChoosen1(boolean fromTableChoosen1) {
        isFromTableChoosen1 = fromTableChoosen1;
    }

    public boolean isFromTableChoosen2() {
        return isFromTableChoosen2;
    }

    public void setFromTableChoosen2(boolean fromTableChoosen2) {
        isFromTableChoosen2 = fromTableChoosen2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Battle battle = (Battle) o;

        if (id != battle.id) return false;
        if (numberOfMove != battle.numberOfMove) return false;
        if (isMove1 != battle.isMove1) return false;
        if (mana1 != battle.mana1) return false;
        if (mana2 != battle.mana2) return false;
        if (hp1 != battle.hp1) return false;
        if (hp2 != battle.hp2) return false;
        if (isHeroPowered1 != battle.isHeroPowered1) return false;
        if (isHeroPowered2 != battle.isHeroPowered2) return false;
        if (isFromHandChoosen1 != battle.isFromHandChoosen1) return false;
        if (isFromHandChoosen2 != battle.isFromHandChoosen2) return false;
        if (isFromTableChoosen1 != battle.isFromTableChoosen1) return false;
        if (isFromTableChoosen2 != battle.isFromTableChoosen2) return false;
        if (deck1 != null ? !deck1.equals(battle.deck1) : battle.deck1 != null) return false;
        if (deck2 != null ? !deck2.equals(battle.deck2) : battle.deck2 != null) return false;
        if (inHand1 != null ? !inHand1.equals(battle.inHand1) : battle.inHand1 != null) return false;
        if (inHand2 != null ? !inHand2.equals(battle.inHand2) : battle.inHand2 != null) return false;
        if (onTable1 != null ? !onTable1.equals(battle.onTable1) : battle.onTable1 != null) return false;
        return onTable2 != null ? onTable2.equals(battle.onTable2) : battle.onTable2 == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (deck1 != null ? deck1.hashCode() : 0);
        result = 31 * result + (deck2 != null ? deck2.hashCode() : 0);
        result = 31 * result + (inHand1 != null ? inHand1.hashCode() : 0);
        result = 31 * result + (inHand2 != null ? inHand2.hashCode() : 0);
        result = 31 * result + (onTable1 != null ? onTable1.hashCode() : 0);
        result = 31 * result + (onTable2 != null ? onTable2.hashCode() : 0);
        result = 31 * result + numberOfMove;
        result = 31 * result + (isMove1 ? 1 : 0);
        result = 31 * result + mana1;
        result = 31 * result + mana2;
        result = 31 * result + hp1;
        result = 31 * result + hp2;
        result = 31 * result + (isHeroPowered1 ? 1 : 0);
        result = 31 * result + (isHeroPowered2 ? 1 : 0);
        result = 31 * result + (isFromHandChoosen1 ? 1 : 0);
        result = 31 * result + (isFromHandChoosen2 ? 1 : 0);
        result = 31 * result + (isFromTableChoosen1 ? 1 : 0);
        result = 31 * result + (isFromTableChoosen2 ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "id=" + id +
                ", deck1=" + deck1 +
                ", deck2=" + deck2 +
                ", inHand1=" + inHand1 +
                ", inHand2=" + inHand2 +
                ", onTable1=" + onTable1 +
                ", onTable2=" + onTable2 +
                ", numberOfMove=" + numberOfMove +
                ", isMove1=" + isMove1 +
                ", mana1=" + mana1 +
                ", mana2=" + mana2 +
                ", hp1=" + hp1 +
                ", hp2=" + hp2 +
                ", isHeroPowered1=" + isHeroPowered1 +
                ", isHeroPowered2=" + isHeroPowered2 +
                ", isFromHandChoosen1=" + isFromHandChoosen1 +
                ", isFromHandChoosen2=" + isFromHandChoosen2 +
                ", isFromTableChoosen1=" + isFromTableChoosen1 +
                ", isFromTableChoosen2=" + isFromTableChoosen2 +
                '}';
    }
}

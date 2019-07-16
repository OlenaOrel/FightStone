package entity;

import dto.UserDto;

import java.util.List;
import java.util.Objects;

public class Battle {
    private int id;

    private UserDto player1;
    private UserDto player2;

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

    private boolean IsHeroUsePower;

    private Integer fromHandChoosen;
    private Integer fromTableChoosen;

    private int battlePointsPlayer1;
    private int battlePointsPlayer2;

    public Battle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getPlayer1() {
        return player1;
    }

    public void setPlayer1(UserDto player1) {
        this.player1 = player1;
    }

    public UserDto getPlayer2() {
        return player2;
    }

    public void setPlayer2(UserDto player2) {
        this.player2 = player2;
    }

    public List<Card> getDeck1() {
        return deck1;
    }

    public void setDeck1(List<Card> deck1) {
        this.deck1 = deck1;
    }

    public List<Card> getDeck2() {
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

    public Integer getFromHandChoosen() {
        return fromHandChoosen;
    }

    public void setFromHandChoosen(Integer fromHandChoosen) {
        this.fromHandChoosen = fromHandChoosen;
    }

    public Integer getFromTableChoosen() {
        return fromTableChoosen;
    }

    public void setFromTableChoosen(Integer fromTableChoosen) {
        this.fromTableChoosen = fromTableChoosen;
    }

    public boolean isHeroUsePower() {
        return IsHeroUsePower;
    }

    public void setHeroUsePower(boolean heroUsePower) {
        IsHeroUsePower = heroUsePower;
    }

    public int getBattlePointsPlayer1() {
        return battlePointsPlayer1;
    }

    public void setBattlePointsPlayer1(int battlePointsPlayer1) {
        this.battlePointsPlayer1 = battlePointsPlayer1;
    }

    public int getBattlePointsPlayer2() {
        return battlePointsPlayer2;
    }

    public void setBattlePointsPlayer2(int battlePointsPlayer2) {
        this.battlePointsPlayer2 = battlePointsPlayer2;
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
        if (IsHeroUsePower != battle.IsHeroUsePower) return false;
        if (battlePointsPlayer1 != battle.battlePointsPlayer1) return false;
        if (battlePointsPlayer2 != battle.battlePointsPlayer2) return false;
        if (!Objects.equals(player1, battle.player1)) return false;
        if (!Objects.equals(player2, battle.player2)) return false;
        if (!Objects.equals(deck1, battle.deck1)) return false;
        if (!Objects.equals(deck2, battle.deck2)) return false;
        if (!Objects.equals(inHand1, battle.inHand1)) return false;
        if (!Objects.equals(inHand2, battle.inHand2)) return false;
        if (!Objects.equals(onTable1, battle.onTable1)) return false;
        if (!Objects.equals(onTable2, battle.onTable2)) return false;
        if (!Objects.equals(fromHandChoosen, battle.fromHandChoosen))
            return false;
        return Objects.equals(fromTableChoosen, battle.fromTableChoosen);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (player1 != null ? player1.hashCode() : 0);
        result = 31 * result + (player2 != null ? player2.hashCode() : 0);
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
        result = 31 * result + (IsHeroUsePower ? 1 : 0);
        result = 31 * result + (fromHandChoosen != null ? fromHandChoosen.hashCode() : 0);
        result = 31 * result + (fromTableChoosen != null ? fromTableChoosen.hashCode() : 0);
        result = 31 * result + battlePointsPlayer1;
        result = 31 * result + battlePointsPlayer2;
        return result;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "id=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
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
                ", IsHeroUsePower=" + IsHeroUsePower +
                ", fromHandChoosen=" + fromHandChoosen +
                ", fromTableChoosen=" + fromTableChoosen +
                ", battlePointsPlayer1=" + battlePointsPlayer1 +
                ", battlePointsPlayer2=" + battlePointsPlayer2 +
                '}';
    }
}

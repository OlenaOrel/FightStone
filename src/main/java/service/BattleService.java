package service;

import collections.Battles;
import entity.Battle;
import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BattleService {

    private final CardService cardService;
    private final Battles battles;

    @Autowired
    public BattleService(CardService cardService, Battles battles) {
        this.cardService = cardService;
        this.battles = battles;
    }

    public int createBattle(User player1, User player2) {
        Battle b = new Battle();
        b.setId(new Random().nextInt());
        b.setPlayer1(player1);
        b.setPlayer2(player2);
        List<Card> p1cards = cardService.getUserCards(player1.getDeck());
        List<Card> p2cards = cardService.getUserCards(player2.getDeck());
        b.setDeck1(p1cards);
        b.setDeck2(p2cards);
        b.setInHand1(cardService.getAndRemoveTwoCardsFromTen(p1cards));
        b.setInHand2(cardService.getAndRemoveTwoCardsFromTen(p2cards));
        b.setOnTable1(new LinkedList<>());
        b.setOnTable2(new LinkedList<>());

        b.setNumberOfMove(1);
        b.setMove1(true);

        b.setMana1(1);
        b.setMana2(1);

        b.setHp1(20);
        b.setHp2(20);

        b.setHeroPowered1(false);
        b.setHeroPowered2(false);
        battles.getBattleList().put(b.getId(), b);

        return b.getId();
    }

    public Battle isUserInBattle(String login) {
        for (Battle b : battles.getBattleList().values()) {
            if (b.getPlayer1().getLogin().equals(login) ||
                    b.getPlayer2().getLogin().equals(login)) {
                return b;
            }
        }
        return null;
    }

    public Battle getBattleById(Integer id) {
        return battles.getBattleList().get(id);
    }

    public Battle inverse(Battle battle) {
        Battle bat = new Battle();
        bat.setPlayer1(battle.getPlayer2());
        bat.setPlayer2(battle.getPlayer1());

        bat.setDeck1(battle.getDeck2());
        bat.setDeck2(battle.getDeck1());

        bat.setInHand1(battle.getInHand2());
        bat.setInHand2(battle.getInHand1());

        bat.setOnTable1(battle.getOnTable2());
        bat.setOnTable2(battle.getOnTable1());

        bat.setMove1(!battle.isMove1());

        bat.setMana1(battle.getMana2());
        bat.setMana2(battle.getMana1());

        bat.setHp1(battle.getHp2());
        bat.setHp2(battle.getHp1());

        bat.setId(battle.getId());
        bat.setNumberOfMove(battle.getNumberOfMove());

        bat.setFromTableChoosen(battle.getFromTableChoosen());
        bat.setFromHandChoosen(battle.getFromHandChoosen());


        return bat;


    }

    public void processEndTurn(String userLogin, Battle b) {
        if (b.getPlayer2().getLogin().equals(userLogin)) {
            b.setNumberOfMove(b.getNumberOfMove() + 1);
        }
        b.setMove1(!b.isMove1());
        //TODO: 35
        b.setMana1(b.getNumberOfMove());
        b.setMana2(b.getNumberOfMove());
        b.setHeroPowered1(false);
        b.setHeroPowered2(false);

        if (b.getPlayer2().getLogin().equals(userLogin)) {
            cardService.moveRandomCard(b.getDeck1(), b.getInHand1());
            cardService.moveRandomCard(b.getDeck2(), b.getInHand2());
        }
        for (Card c : b.getOnTable1()) {
            c.setCardCanMoove(true);
        }
        for (Card c : b.getOnTable2()) {
            c.setCardCanMoove(true);
        }

    }

    public void moveCardById(List<Card> from, List<Card> to, Integer id) {
        for (Card c : from) {
            if (id.equals(c.getId())) {
                to.add(c);
                from.remove(c);
                break;
            }
        }
    }

    public void doPlaceCard(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            for (Card c : b.getInHand1()) {
                if (id.equals(c.getId())) {
                    b.getOnTable1().add(c);
                    b.getInHand1().remove(c);
                    b.setMana1(b.getMana1() - c.getCost());
                    break;
                }
            }
        } else {
            for (Card c : b.getInHand2()) {
                if (id.equals(c.getId())) {
                    b.getOnTable2().add(c);
                    b.getInHand2().remove(c);
                    b.setMana2(b.getMana2() - c.getCost());
                    break;
                }
            }
        }
    }

    public void doChooseOnTableLogic(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            for (Card c : b.getOnTable1()) {
                if (id.equals(c.getId())) {
                    b.setFromTableChoosen(id);
                    break;
                }
            }
        } else {
            for (Card c : b.getOnTable2()) {
                if (id.equals(c.getId())) {
                    b.setFromTableChoosen(id);
                    break;
                }
            }
        }
    }

    public void doUnchooseOnTableLogic(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            for (Card c : b.getOnTable1()) {
                if (id.equals(-c.getId())) {
                    b.setFromTableChoosen(null);
                    break;
                }
            }
        } else {
            for (Card c : b.getOnTable2()) {
                if (id.equals(-c.getId())) {
                    b.setFromTableChoosen(null);
                    break;
                }
            }
        }
    }

    public void doAttackCard(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            Card defender = cardService.chooseCardFromListById(b.getOnTable2(), id);
            Card attacker = cardService.chooseCardFromListById(b.getOnTable1(), b.getFromTableChoosen());
            defender.setArmor(defender.getArmor() - attacker.getDamage());
            attacker.setArmor(attacker.getArmor() - defender.getDamage());
            if (defender.getArmor() <= 0) {
                b.getOnTable2().remove(defender);
            }
            if (attacker.getArmor() <= 0) {
                b.getOnTable1().remove(attacker);
            }
            b.setFromTableChoosen(null);
            attacker.setCardCanMoove(false);
        } else {
            Card defender = cardService.chooseCardFromListById(b.getOnTable1(), id);
            Card attacker = cardService.chooseCardFromListById(b.getOnTable2(), b.getFromTableChoosen());
            defender.setArmor(defender.getArmor() - attacker.getDamage());
            attacker.setArmor(attacker.getArmor() - defender.getDamage());
            if (defender.getArmor() <= 0) {
                b.getOnTable1().remove(defender);
            }
            if (attacker.getArmor() <= 0) {
                b.getOnTable2().remove(attacker);
            }
            b.setFromTableChoosen(null);
            attacker.setCardCanMoove(false);
        }
    }
}

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

        b.setNumberOfMove(0);
        b.setMove1(false);

        b.setMana1(0);
        b.setMana2(0);

        b.setHp1(20);
        b.setHp2(20);

        b.setHeroPowered1(false);
        b.setHeroPowered2(false);

        b.setFromHandChoosen1(false);
        b.setFromHandChoosen2(false);

        b.setFromTableChoosen1(false);
        b.setFromTableChoosen2(false);

        battles.getBattleList().put(b.getId(), b);

        return b.getId();
    }
}

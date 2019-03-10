package service;


import com.google.gson.Gson;
import dao.CardDao;
import entity.Card;
import entity.User;
import holder.CardHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class CardService {

    private final CardDao cardDao;

    @Autowired
    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public Card getById(int id) {
        return cardDao.getById(id);
    }

    public List<Card> getAllCards() {
        return cardDao.getAllCards();
    }

    public List<Card> getUserCards(String json) {
        List<Card> out = new LinkedList<>();
        List<Integer> cards = getCardIdsFromJson(json);
        for (Integer id : cards) {
            out.add(getById(id));
        }
        return out;
    }

    public List<Integer> getCardIdsFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CardHolder.class).cards;
    }

    public boolean isTenCardsInDeck(String json) {
        return getCardIdsFromJson(json).size() == 10;
    }

    public void removeCardFromUserDeck(User u, Integer id) {
        List<Integer> ids = getCardIdsFromJson(u.getDeck());
        ids.remove(id);
        u.setDeck(getJsonFromUserCardsIds(ids));
    }

    public void addCardToUserDeck(User u, Integer id) {
        List<Integer> ids = getCardIdsFromJson(u.getDeck());
        ids.add(id);
        u.setDeck(getJsonFromUserCardsIds(ids));
    }

    public String getJsonFromUserCardsIds(List<Integer> ids) {
        return new Gson().toJson(new CardHolder(ids));
    }

    public List<Card> getAndRemoveTwoCardsFromTen(List<Card> tenCards) {
        List<Card> out = new LinkedList<>();
        out.add(tenCards.remove(new Random().nextInt(tenCards.size())));
        out.add(tenCards.remove(new Random().nextInt(tenCards.size())));
        return out;
    }

    public void moveRandomCard(List<Card> from, List<Card> to) {
        if (from.size() > 0) {
            to.add(from.remove(new Random().nextInt(from.size())));
        }
    }

    public Card chooseCardFromListById(List<Card> list, Integer id) {
        for (Card c : list) {
            if (id.equals(c.getId())) {
                return c;
            }
        }
        return null;
    }


}

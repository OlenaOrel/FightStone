package service;


import com.google.gson.Gson;
import dao.CardDao;
import dto.UserDto;
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

    private Card getById(int id) {
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

    private List<Integer> getCardIdsFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CardHolder.class).cards;
    }

    public boolean isTenCardsInDeck(List<Card> deck) {
        return deck.size() == 10;
    }

    public void removeCardFromUserDeck(UserDto uDto, Integer id) {
        List<Card> tmp = uDto.getDeck();
        for (int i = 0; i < tmp.size(); i++) {
            if (id.equals(tmp.get(i).getId())) {
                tmp.remove(tmp.get(i));
            }
        }
//        for (Card card : tmp){
//            if (id.equals(card.getId())){
//                tmp.remove(card);
//            }
//        }
        uDto.setDeck(tmp);
    }

    public void addCardToUserDeck(UserDto uDto, Integer id) {
        List<Card> tmp = uDto.getDeck();
        tmp.add(getById(id));
        uDto.setDeck(tmp);
    }

    public List<Integer> getCardIdsFromUserDeck(List<Card> deck) {
        List<Integer> out = new LinkedList<>();
        for (Card c : deck) {
            out.add(c.getId());
        }
        return out;
    }

    private String getJsonFromUserCardsIds(List<Integer> ids) {
        return new Gson().toJson(new CardHolder(ids));
    }

    public void updateUserDeck(User u, List<Integer> ids) {
        u.setDeck(getJsonFromUserCardsIds(ids));
    }

    List<Card> getAndRemoveTwoCardsFromTen(List<Card> tenCards) {
        List<Card> out = new LinkedList<>();
        out.add(tenCards.remove(new Random().nextInt(tenCards.size())));
        out.add(tenCards.remove(new Random().nextInt(tenCards.size())));
        return out;
    }

    void moveRandomCard(List<Card> from, List<Card> to) {
        if (from.size() > 0) {
            to.add(from.remove(new Random().nextInt(from.size())));
        }
    }

    Card chooseCardFromListById(List<Card> list, Integer id) {
        for (Card c : list) {
            if (id.equals(c.getId())) {
                return c;
            }
        }
        return null;
    }


}

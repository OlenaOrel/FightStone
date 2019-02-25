package service;


import com.google.gson.Gson;
import dao.CardDao;
import entity.Card;
import holder.CardHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CardService {

    private final CardDao cardDao;

    @Autowired
    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public Card getById(int id){
        return cardDao.getById(id);
    }

    public List<Card> getAllCards() {
        return cardDao.getAllCards();
    }

    public List<Card> getUserCards(String json) {
        List<Card> out = new LinkedList<>();
        Gson gson = new Gson();
        CardHolder cards = gson.fromJson(json, CardHolder.class);
        for (Integer c : cards.cards) {
            out.add(cardDao.getById(c));
        }
        return out;

    }
}

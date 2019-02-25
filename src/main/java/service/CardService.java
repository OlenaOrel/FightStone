package service;


import dao.CardDao;
import entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

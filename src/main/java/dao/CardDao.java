package dao;

import entity.Card;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardDao {
    public Card getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Card card = (Card) s.createQuery(String.format("FROM Card WHERE id='%s'", id)).uniqueResult();
        s.close();
        return card;
    }

    public List<Card> getAllCards() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Card> cards = s.createQuery("FROM Card").list();
        s.close();
        return cards;
    }

}

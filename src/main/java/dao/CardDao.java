package dao;

import entity.Card;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CardDao {
    public Card getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Card card = (Card) s.createQuery(String.format("FROM Card WHERE id='%s'", id)).uniqueResult();
        s.close();
        return card;
    }

    public List<Card> getAllCards() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Card> cards = s.createCriteria("FROM Card").list();
        s.close();
        return cards;
    }

}

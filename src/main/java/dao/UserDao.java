package dao;

import entity.User;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    public User getByLogin(String login){
        Session s = HibernateUtil.getSessionFactory().openSession();
        User out = (User) s.createQuery(String.format("FROM User WHERE login='%s'", login)).uniqueResult();
        s.close();
        return out;
    }

    public void add(User u){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
    }

    public void update(String login, int points, int level, int stars, String deck) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        User u = (User) s.load(User.class, login);
        tx.commit();

        u.setStars(stars);
        u.setLvl(level);
        u.setPoints(points);
        u.setDeck(deck);
        Transaction tx1 = s.beginTransaction();
        s.update(u);
        tx1.commit();
        s.close();

    }
}

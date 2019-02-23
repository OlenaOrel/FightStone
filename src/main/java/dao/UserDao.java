package dao;

import entity.User;
import hibernate.HibernateUtil;
import org.hibernate.Session;
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

    public void update(User u) {

    }
}

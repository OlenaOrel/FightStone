package service;

import collections.UsersOnline;
import dao.UserDao;
import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private static final String USER = "user";

    private final UserDao udao;
    private final UsersOnline usersOnline;

    @Autowired
    public UserService(UserDao udao, UsersOnline usersOnline) {
        this.udao = udao;
        this.usersOnline = usersOnline;
    }

    public boolean isUserExists(String login) {
        return udao.getByLogin(login) != null;
    }

    public User save(String login, String pass) {
        User u = new User(new Random().nextInt(), login, pass,
                0, 0, 0, "{}", "warrior");
        udao.add(u);
        return u;
    }

    public User getByLogin(String login) {
        return udao.getByLogin(login);
    }

    public boolean isPassConfirm(String s1, String s2) {
        return s1 != null && s1.equals(s2);
    }

    public void setUserAttributeToSession(HttpSession session, User u) {
        session.setAttribute(USER, u);
    }

    public User getUserAttributeFromSession(HttpSession s) {
        return (User) s.getAttribute(USER);
    }

    public void update(User u) {
        udao.update(u);
    }


    public void removeExistsCards(List<Card> allCards, List<Card> userCards) {
        allCards.removeAll(userCards);
    }

    public void addUsersOnline(User u) {
        usersOnline.getUsersOnline().put(u.getLogin(), u);
    }

    public void removeUserFromSession(String login) {
        usersOnline.getUsersOnline().remove(login);
    }
}
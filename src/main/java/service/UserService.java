package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class UserService {

    private final UserDao udao;

    @Autowired
    public UserService(UserDao udao) {
        this.udao = udao;
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
        session.setAttribute("user", u);
    }

}
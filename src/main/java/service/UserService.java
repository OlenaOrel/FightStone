package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    private final UserDao udao;

    @Autowired
    public UserService(UserDao udao) {
        this.udao = udao;
    }

    public boolean isUserExists(String login){
        return udao.getByLogin(login) != null;
    }

    public void save(String login, String pass){
        udao.add(new User(new Random().nextInt(), login, pass,
                0, 0, 0, "{}", "warrior"));
    }

    public User getByLogin(String login){
        return udao.getByLogin(login);
    }
}

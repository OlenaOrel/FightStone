package service;

import collections.UsersOnline;
import dao.UserDao;
import dto.UserDto;
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
    private static final String UDTO = "udto";

    private final UserDao udao;
    private final UsersOnline usersOnline;
    private final CardService cardService;

    @Autowired
    public UserService(UserDao udao, UsersOnline usersOnline, CardService cardService) {
        this.udao = udao;
        this.usersOnline = usersOnline;
        this.cardService = cardService;
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

    public void setUserAttributeToSession(HttpSession session, User u, UserDto uDto) {
        session.setAttribute(USER, u);
        session.setAttribute(UDTO, uDto);
    }

    public User getUserAttributeFromSession(HttpSession s) {
        return (User) s.getAttribute(USER);
    }

    public UserDto getUserDtoAttributeFromSession(HttpSession s) {
        return (UserDto) s.getAttribute(UDTO);
    }

    public void update(User u) {
        udao.update(u);
    }


    public void removeExistsCards(List<Card> allCards, List<Card> userCards) {
        allCards.removeAll(userCards);
    }

    public void addUsersOnline(UserDto userDto) {
        usersOnline.getUsersOnline().put(userDto.getLogin(), userDto);
    }

    public void removeUserFromSession(String login) {
        usersOnline.getUsersOnline().remove(login);
    }

    public UserDto getUserDto(User u) {
        UserDto userDto = new UserDto();
        userDto.setId(u.getId());
        userDto.setLogin(u.getLogin());
        userDto.setPass(u.getPass());
        userDto.setPoints(u.getPoints());
        userDto.setLvl(u.getLvl());
        userDto.setStars(u.getStars());
        userDto.setDeck(cardService.getUserCards(u.getDeck()));
        userDto.setClas(u.getClas());
        return userDto;
    }
}
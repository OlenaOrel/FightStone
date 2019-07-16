package dto;

import entity.Card;

import java.util.List;

public class UserDto {

    private int id;
    private String login;
    private String pass;
    private int points;
    private int lvl;
    private int stars;
    private List<Card> deck;
    private String clas;

    public UserDto() {
    }

    public UserDto(int id, String login, String pass, int points, int lvl, int stars, List<Card> deck, String clas) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.points = points;
        this.lvl = lvl;
        this.stars = stars;
        this.deck = deck;
        this.clas = clas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
}

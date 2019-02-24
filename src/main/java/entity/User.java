package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "fs_user")
@Entity
public class User implements Serializable {
    private int id;
    @Id
    private String login;
    private String pass;
    private int points;
    private int lvl;
    private int stars;
    private String deck;
    @Column(name = "class")
    private String clas;

    public User() {
    }

    public User(int id, String login, String pass, int points, int lvl, int stars, String deck, String clas) {
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

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                points == user.points &&
                lvl == user.lvl &&
                stars == user.stars &&
                Objects.equals(login, user.login) &&
                Objects.equals(pass, user.pass) &&
                Objects.equals(deck, user.deck) &&
                Objects.equals(clas, user.clas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, pass, points, lvl, stars, deck, clas);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", points=" + points +
                ", lvl=" + lvl +
                ", stars=" + stars +
                ", deck='" + deck + '\'' +
                ", clas='" + clas + '\'' +
                '}';
    }
}

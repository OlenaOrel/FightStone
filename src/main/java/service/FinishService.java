package service;

import collections.ActivePlayers;
import collections.Battles;
import dao.UserDao;
import dto.UserDto;
import entity.Battle;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class FinishService {
    private final Battles battles;
    private final UserDao userDao;
    private final ActivePlayers activePlayers;
    private static boolean firstExit = false;
    @Autowired
    private FinishService(UserDao userDao, Battles battles, ActivePlayers activePlayers) {
        this.userDao = userDao;
        this.battles = battles;
        this.activePlayers = activePlayers;
    }


    private Battle calculatePoints(Battle b) {
        UserDto u1 = b.getPlayer1();
        UserDto u2 = b.getPlayer2();
        List<UserDto> winnerAndLooser;
        if (b.getHp1() <= 0) {
            winnerAndLooser = calculating(u2, u1, b);
            u1 = winnerAndLooser.remove(1);
            u2 = winnerAndLooser.remove(0);
            u1.setPoints(u1.getPoints() + (b.getBattlePointsPlayer1() / 2));
            u2.setPoints(u2.getPoints() + b.getBattlePointsPlayer2());

        } else if (b.getHp2() <= 0) {
            winnerAndLooser = calculating(u1, u2, b);
            u2 = winnerAndLooser.remove(1);
            u1 = winnerAndLooser.remove(0);
            u2.setPoints(u2.getPoints() + (b.getBattlePointsPlayer2() / 2));
            u1.setPoints(u1.getPoints() + b.getBattlePointsPlayer1());
        }
        b.setPlayer1(u1);
        b.setPlayer2(u2);
        return b;
    }

    private List<UserDto> calculating(UserDto winner, UserDto looser, Battle b) {
        if (looser.getStars() == 0 && looser.getLvl() > 0) {
            looser.setLvl(looser.getLvl() - 1);
            looser.setStars(4);
        } else if (looser.getLvl() > 0 && looser.getStars() >= 0) {
            looser.setStars(looser.getStars() - 1);
        }
        winner.setStars(winner.getStars() + 1);
        if (winner.getStars() == 5) {
            winner.setLvl(winner.getLvl() + 1);
            winner.setStars(0);
        }
        List<UserDto> usersInBattle = new LinkedList<>();
        Collections.addAll(usersInBattle, winner, looser);
        return usersInBattle;
    }

    public void deleteBattle(Integer id) {
        battles.getBattleList().remove(id);
    }

    public void deleteActivePlayers(Battle b) {
        if (activePlayers.getActivePlayersList().containsKey(b.getPlayer1().getLogin())) {
            activePlayers.getActivePlayersList().remove(b.getPlayer1().getLogin());
        }
        if (activePlayers.getActivePlayersList().containsKey(b.getPlayer2().getLogin())) {
            activePlayers.getActivePlayersList().remove(b.getPlayer2().getLogin());
        }
    }

    public Battle result(Battle bat) {
        calculatePoints(bat);
        return bat;
    }

    public boolean isFirstExit() {
        return firstExit;
    }

    public void setFirstExit() {
        firstExit = true;
    }

    public void notFirstExiting() {
        firstExit = false;
    }

    public void updateUserPoints(UserDto userDto, User u) {
        u.setPoints(userDto.getPoints());
        u.setStars(userDto.getStars());
        u.setLvl(userDto.getLvl());
        userDao.update(u);
    }

}

package controller;

import collections.WaitUsers;
import entity.Battle;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.BattleService;
import service.UserService;
import service.WaitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/wait")
public class WaitController {
    private final UserService userService;
    private final WaitService waitService;
    private final BattleService battleService;
    private final WaitUsers waitUsers;

    @Autowired
    public WaitController(UserService userService, WaitUsers waitUsers, WaitService waitService, BattleService battleService) {
        this.userService = userService;
        this.waitUsers = waitUsers;
        this.waitService = waitService;
        this.battleService = battleService;
    }

    @GetMapping
    public ModelAndView waitPage(HttpServletRequest req,
                                 HttpServletResponse resp) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null) {
            if (waitUsers.getWaitList().size() == 1) {
                return new ModelAndView("wait", "u", u);
            } else {
                req.getSession().getId();
                resp.sendRedirect("/fs/battle/");
                return null;
            }
        } else {
            req.getSession().getId();
            resp.sendRedirect("/fs/battle/");
        }
        return null;
    }

    @PostMapping
    public void waitLogic(HttpServletRequest req,
                          HttpServletResponse resp,
                          @RequestParam String bat) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null) {
            if (bat.equals("in")) {
                if (!waitService.isWaitListEmpty() && !waitService.waitUsersListContainsUserByLogin(u.getLogin())) {
                    User oppUser = waitService.getUserForBattle();
                    int battleId = battleService.createBattle(u, oppUser);
                    req.getSession().setAttribute("battleId", battleId);
                    battleService.addActivePlayer(u, oppUser, battleId);
                    resp.sendRedirect("/fs/battle/");
                } else {
                    Battle b = battleService.getBattleById(battleService.isUserInBattle2(u.getLogin()));
                    if (b != null) {
                        req.getSession().setAttribute("battleId", b.getId());
                        resp.sendRedirect("/fs/battle/");
                    } else {
                        if (!waitService.waitUsersListContainsUserByLogin(u.getLogin())) {
                            waitUsers.getWaitList().put(u.getLogin(), u);
                        }
                        resp.sendRedirect("/fs/wait/");
                    }
                }


            }
            if (bat.equals("out")) {
                if (waitUsers.getWaitList().containsKey(u.getLogin())) {
                    waitUsers.getWaitList().remove(u.getLogin());
                }
                resp.sendRedirect("/fs/main/");
            }
        } else {
            resp.sendRedirect("/fs/");
        }
    }
}

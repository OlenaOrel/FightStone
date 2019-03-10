package controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/battle")
public class BattleController {
    private final UserService userService;
    private final BattleService battleService;

    @Autowired
    public BattleController(UserService userService, BattleService battleService) {
        this.userService = userService;
        this.battleService = battleService;
    }

    @GetMapping
    public ModelAndView battleView(HttpServletRequest req,
                                   HttpServletResponse resp) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null && u.equals(battleService.isUserInBattle(u.getLogin()).getPlayer1())) {
            return new ModelAndView("battle", "b", battleService.getBattleById((Integer) req.getSession().getAttribute("battleId")));
        } else if (u != null && u.equals(battleService.isUserInBattle(u.getLogin()).getPlayer2())) {
            return new ModelAndView("battle", "b", battleService.inverse(battleService.getBattleById((Integer) req.getSession().getAttribute("battleId"))));
        } else {
            resp.sendRedirect("/fs/");
            return null;
        }
    }

    @PostMapping
    public void battle(HttpServletResponse resp,
                       HttpServletRequest req,
                       @RequestParam(required = false) Integer table,
                       @RequestParam(required = false) Integer hand,
                       @RequestParam(required = false) Integer attack,
                       @RequestParam(required = false) String endTurn) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null) {
            Integer battleId = (Integer) req.getSession().getAttribute("battleId");
            Battle b = battleService.getBattleById(battleId);
            if (endTurn != null) {
                battleService.processEndTurn(u.getLogin(), b);
            }
            if (hand != null) {
                battleService.doPlaceCard(b, u.getLogin(), hand);
            }
            if (table != null) {
                if (table < 0) {
                    battleService.doUnchooseOnTableLogic(b, u.getLogin(), table);
                }
                battleService.doChooseOnTableLogic(b, u.getLogin(), table);
            }
            if (attack != null) {
                battleService.doAttackCard(b, u.getLogin(), attack);
            }
            resp.sendRedirect("/fs/battle/");
        } else {
            resp.sendRedirect("/fs/");
        }
    }
}


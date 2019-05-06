package controller;

import entity.Battle;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.BattleService;
import service.FinishService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/finish")
public class FinishController {
    private final UserService userService;
    private final FinishService finishService;
    private final BattleService battleService;


    @Autowired
    public FinishController(UserService userService, BattleService battleService, FinishService finishService) {
        this.userService = userService;
        this.finishService = finishService;
        this.battleService = battleService;
    }


    @GetMapping
    public ModelAndView finishView(HttpServletRequest req,
                                   HttpServletResponse resp) {
        User u = userService.getUserAttributeFromSession(req.getSession());
        Battle b = finishService.result(battleService.getBattleById((Integer) req.getSession().getAttribute("battleId")));
        if (u != null && u.equals(battleService.getBattleById(battleService.isUserInBattle2(u.getLogin())).getPlayer1())) {
            return new ModelAndView("finish", "b", b);
        } else if (u != null && u.equals(battleService.getBattleById(battleService.isUserInBattle2(u.getLogin())).getPlayer2())) {
            return new ModelAndView("finish", "b", battleService.inverse(b));
        } else {
            return new ModelAndView("login");
        }

    }

    @PostMapping
    public void battle(HttpServletResponse resp,
                       HttpServletRequest req, HttpSession s) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null) {
            if (!finishService.isFirstExit()) {
                finishService.setFirstExit();
                req.getSession().setAttribute("battleId", 0);
                resp.sendRedirect("/fs/main");
                return;
            }
            finishService.notFirstExiting();
            Integer battleId = (Integer) req.getSession().getAttribute("battleId");
            Battle b = battleService.getBattleById(battleId);
            finishService.deleteActivePlayers(b);
            finishService.deleteBattle((Integer) req.getSession().getAttribute("battleId"));
            req.getSession().setAttribute("battleId", null);
            resp.sendRedirect("/fs/main/");
        } else {
            resp.sendRedirect("/fs/");
        }
    }


}

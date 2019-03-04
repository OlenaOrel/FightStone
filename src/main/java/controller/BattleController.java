package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        if (u != null) {
            return new ModelAndView("battle", "b", battleService.getBattleById((Integer) req.getSession().getAttribute("battleId")));
        } else {
            resp.sendRedirect("/fs/");
            return null;
        }
    }

}

package controller;

import dto.UserDto;
import entity.Battle;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.BattleService;
import service.CardService;
import service.FinishService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/finish")
public class FinishController {
    private final UserService userService;
    private final FinishService finishService;
    private final BattleService battleService;
    private final CardService cardService;


    @Autowired
    public FinishController(UserService userService, BattleService battleService, FinishService finishService, CardService cardService) {
        this.userService = userService;
        this.finishService = finishService;
        this.battleService = battleService;
        this.cardService = cardService;
    }


    @GetMapping
    public ModelAndView finishView(HttpServletRequest req,
                                   HttpServletResponse resp) {
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        Battle b = finishService.result(battleService.getBattleById((Integer) req.getSession().getAttribute("battleId")));
        if (uDto != null && battleService.isUserDtoPlayer1(uDto, b)) {
            User u = userService.getUserAttributeFromSession(req.getSession());
            if (u != null) {
                finishService.updateUserPoints(uDto, u);
                uDto.setDeck(cardService.getUserCards(u.getDeck()));
            }
            return new ModelAndView("finish", "b", b);
        } else if (uDto != null && battleService.isUserDtoPlayer1(uDto, battleService.inverse(b))) {
            User u = userService.getUserAttributeFromSession(req.getSession());
            if (u != null) {
                finishService.updateUserPoints(uDto, u);
                uDto.setDeck(cardService.getUserCards(u.getDeck()));
            }
            return new ModelAndView("finish", "b", battleService.inverse(b));
        } else {
            return new ModelAndView("login");
        }

    }

    @PostMapping
    public void finish(HttpServletResponse resp,
                       HttpServletRequest req) throws IOException {
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        if (uDto != null) {
            if (!finishService.isFirstExit()) {
                finishService.setFirstExit();
                req.getSession().setAttribute("battleId", 0);
                resp.sendRedirect("/fs/main");
                return;
            }
            finishService.notFirstExiting();
            Battle b = battleService.getBattleById((Integer) req.getSession().getAttribute("battleId"));
            finishService.deleteActivePlayers(b);
            finishService.deleteBattle((Integer) req.getSession().getAttribute("battleId"));
            req.getSession().setAttribute("battleId", null);
            resp.sendRedirect("/fs/main/");
        } else {
            resp.sendRedirect("/fs/");
        }
    }
}

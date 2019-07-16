package controller;

import dto.UserDto;
import entity.Battle;
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
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
//        if (u != null && u.equals(battleService.isUserInBattle(u.getLogin()).getPlayer1())) {
        if (uDto != null && uDto.equals(battleService.getBattleById(battleService.isUserInBattle2(uDto.getLogin())).getPlayer1())) {
            return new ModelAndView("battle", "b", battleService.getBattleById((Integer) req.getSession().getAttribute("battleId")));
//        } else if (u != null && u.equals(battleService.isUserInBattle(u.getLogin()).getPlayer2())) {
        } else if (uDto != null && uDto.equals(battleService.getBattleById(battleService.isUserInBattle2(uDto.getLogin())).getPlayer2())) {
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
                       @RequestParam(required = false) String endTurn,
                       @RequestParam(required = false) String power,
                       @RequestParam(required = false) String attackHero) throws IOException {
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        if (uDto != null) {
            Integer battleId = (Integer) req.getSession().getAttribute("battleId");
            Battle b = battleService.getBattleById(battleId);
            if (endTurn != null) {
                battleService.processEndTurn(uDto.getLogin(), b);
            }
            if (hand != null) {
                battleService.doPlaceCard(b, uDto.getLogin(), hand);
            }
            if (power != null) {
                battleService.doHeroPower(b, uDto.getLogin());
            }
            if (attackHero != null) {
                battleService.doAttackHero(b, uDto.getLogin());
            }
            if (table != null) {
                if (table < 0) {
                    battleService.doUnchooseOnTableLogic(b, uDto.getLogin(), table);
                }
                battleService.doChooseOnTableLogic(b, uDto.getLogin(), table);
            }
            if (attack != null) {
                battleService.doAttackCard(b, uDto.getLogin(), attack);
            }

            if (battleService.isSomeHeroDead(b)) {
                battleService.processEndTurn(uDto.getLogin(), b);
                req.getSession().setAttribute("battleId", b.getId());
                resp.sendRedirect("/fs/finish/");
            } else {
                resp.sendRedirect("/fs/battle/");
            }
        } else {
            resp.sendRedirect("/fs/");
        }
    }
}


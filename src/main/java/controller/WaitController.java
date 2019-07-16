package controller;

import collections.WaitUsers;
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
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        if (uDto != null) {
            if (waitUsers.getWaitList().size() == 1) {
                return new ModelAndView("wait", "uDto", uDto);
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
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        if (uDto != null) {
            if (bat.equals("in")) {
                if (!waitService.isWaitListEmpty() && !waitService.waitUsersListContainsUserByLogin(uDto.getLogin())) {
                    UserDto oppUser = waitService.getUserForBattle();
                    int battleId = battleService.createBattle(uDto, oppUser);
                    req.getSession().setAttribute("battleId", battleId);
                    battleService.addActivePlayer(uDto, oppUser, battleId);
                    resp.sendRedirect("/fs/battle/");
                } else {
                    Battle b = battleService.getBattleById(battleService.isUserInBattle2(uDto.getLogin()));
                    if (b != null) {
                        req.getSession().setAttribute("battleId", b.getId());
                        resp.sendRedirect("/fs/battle/");
                    } else {
                        if (!waitService.waitUsersListContainsUserByLogin(uDto.getLogin())) {
                            waitUsers.getWaitList().put(uDto.getLogin(), uDto);
                        }
                        resp.sendRedirect("/fs/wait/");
                    }
                }


            }
            if (bat.equals("out")) {
                if (waitUsers.getWaitList().containsKey(uDto.getLogin())) {
                    waitUsers.getWaitList().remove(uDto.getLogin());
                }
                resp.sendRedirect("/fs/main/");
            }
        } else {
            resp.sendRedirect("/fs/");
        }
    }
}

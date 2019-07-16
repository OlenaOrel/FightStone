package controller;

import collections.ActivePlayers;
import collections.UsersOnline;
import collections.WaitUsers;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/main")
public class MainController {
    private final UserService userService;
    private final WaitUsers waitUsers;
    private final UsersOnline usersOnline;
    private final ActivePlayers activePlayers;
    @Autowired
    public MainController(UserService userService, WaitUsers waitUsers, UsersOnline usersOnline, ActivePlayers activePlayers) {
        this.userService = userService;
        this.waitUsers = waitUsers;
        this.usersOnline = usersOnline;
        this.activePlayers = activePlayers;
    }

    @GetMapping
    public ModelAndView mainPage(HttpServletResponse resp,
                                 HttpServletRequest req) throws IOException {
        UserDto uDto = userService.getUserDtoAttributeFromSession(req.getSession());
        if (uDto != null) {
            ModelAndView model = new ModelAndView("main");
            model.addObject("uDto", uDto);
            model.addObject("userCards", uDto.getDeck());
            model.addObject("wait", waitUsers.getWaitList().size());
            model.addObject("online", usersOnline.getUsersOnline().size());
            model.addObject("active", activePlayers.getActivePlayersList().size());
            return model;
        } else {
            resp.sendRedirect("/fs/");
        }
        return null;
    }

    @PostMapping
    public void logOut(HttpSession session,
                       HttpServletResponse response,
                       @RequestParam String logout) throws IOException {
        session.setAttribute("user", null);
        session.setAttribute("uDto", null);
        userService.removeUserFromSession(logout);
        response.sendRedirect("/fs/");
    }
}

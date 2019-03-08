package controller;

import collections.UsersOnline;
import collections.WaitUsers;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/main")
public class MainController {
    private final CardService cardService;
    private final UserService userService;
    private final WaitUsers waitUsers;
    private final UsersOnline usersOnline;
    @Autowired
    public MainController(UserService userService, CardService cardService, WaitUsers waitUsers, UsersOnline usersOnline) {
        this.userService = userService;
        this.cardService = cardService;
        this.waitUsers = waitUsers;
        this.usersOnline = usersOnline;
    }

    @GetMapping
    public ModelAndView mainPage(HttpServletResponse resp,
                                 HttpServletRequest req) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null) {
            ModelAndView model = new ModelAndView("main");
            model.addObject("u", u);
            model.addObject("userCards", cardService.getUserCards(u.getDeck()));
            model.addObject("wait", waitUsers.getWaitList().size());
            model.addObject("online", usersOnline.getUsersOnline().size());
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
        userService.removeUserFromSession(logout);
        response.sendRedirect("/fs/");
    }
}

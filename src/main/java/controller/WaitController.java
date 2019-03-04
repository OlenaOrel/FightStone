package controller;

import collections.Battles;
import collections.WaitUsers;
import entity.User;
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
import java.io.IOException;

@Controller
@RequestMapping("/wait")
public class WaitController {
    private final UserService userService;
    private final WaitUsers waitUsers;
    private final Battles battles;
    @Autowired
    public WaitController(UserService userService, WaitUsers waitUsers, Battles battles) {
        this.userService = userService;
        this.waitUsers = waitUsers;
        this.battles = battles;
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
                if (!waitUsers.getWaitList().containsKey(u.getLogin())) {
                    waitUsers.getWaitList().put(u.getLogin(), u);
                }
                resp.sendRedirect("/fs/wait/");

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

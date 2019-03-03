package controller;

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

    @Autowired
    public WaitController(UserService userService, WaitUsers waitUsers) {
        this.userService = userService;
        this.waitUsers = waitUsers;
    }

    @GetMapping
    public ModelAndView waitPage(HttpServletRequest req,
                                 HttpServletResponse resp) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null) {
            return new ModelAndView("wait", "u", u);
        } else {
            resp.sendRedirect("/fs/");
            return null;
        }
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

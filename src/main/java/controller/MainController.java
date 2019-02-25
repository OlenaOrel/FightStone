package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/main")
public class MainController {
    private final CardService cardService;
    private final UserService userService;

    @Autowired
    public MainController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping
    public ModelAndView mainPage(HttpServletResponse resp,
                                 HttpServletRequest req) throws IOException {
        User u = userService.getUserAttributeFromSession(req.getSession());
        if (u != null) {
            ModelAndView model = new ModelAndView("main");
            model.addObject("u", u);
            model.addObject("userCards", cardService.getUserCards(u.getDeck()));
            return model;
        } else {
            resp.sendRedirect("/fs/");
        }
        return null;
    }
}

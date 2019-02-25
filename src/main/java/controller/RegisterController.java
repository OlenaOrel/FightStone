package controller;

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
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView registerView() {
        return new ModelAndView("register");
    }

    @PostMapping
    public void register(@RequestParam String login,
                         @RequestParam String pass,
                         @RequestParam String cpass,
                         HttpServletResponse resp,
                         HttpServletRequest req) throws IOException {
        if (!userService.isUserExists(login) && userService.isPassConfirm(pass, cpass)) {
            User u = userService.save(login, pass);
            userService.setUserAttributeToSession(req.getSession(), u);
            resp.sendRedirect("/fs/main/");

        } else {
            resp.sendRedirect("/fs/");
        }
    }
}

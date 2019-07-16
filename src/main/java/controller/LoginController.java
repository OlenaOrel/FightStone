package controller;


import dto.UserDto;
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
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView loginView(){
        return new ModelAndView("login");
    }

    @PostMapping
    public void login(@RequestParam String login,
                      @RequestParam String pass,
                      HttpServletRequest req,
                      HttpServletResponse resp) throws IOException {
        User u = userService.getByLogin(login);
        if(u != null && u.getPass().equals(pass)){
            UserDto uDto = userService.getUserDto(u);
            userService.setUserAttributeToSession(req.getSession(), u, uDto);
            userService.addUsersOnline(uDto);
            resp.sendRedirect("/fs/main/");
        }else{
            resp.sendRedirect("/fs/");
        }
    }
}

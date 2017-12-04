package blog.controllers;

import blog.forms.LoginForm;
import blog.services.LoginService;
import blog.services.NotificationService;
import blog.services.PasswordService;
import blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/users/login")
    public String login(LoginForm loginForm) {
        return "users/login";
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String loginPage(@Valid LoginForm loginForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Opanki!!! Please fill the form correctly!");
            return "users/login";
        }

        if (!passwordService.authenticate(
                loginForm.getPassword().toCharArray(), userService
                        .findById(userService.findByName(loginForm.getUsername()))
                        .getPasswordHash())) {
            notifyService.addErrorMessage("Not Correct login!!!!");
            return "users/login";
        }

        loginService.setUser(userService.findById(userService.findByName(loginForm.getUsername())));
        notifyService.addInfoMessage(" Login successful !!!!");
        return "redirect:/";
    }

    @RequestMapping("/users/logout")
    public String logout(){
        loginService.setUser(null);
        return "redirect:/";
    }
}
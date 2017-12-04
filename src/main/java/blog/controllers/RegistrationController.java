package blog.controllers;


import blog.forms.RegistrationForm;
import blog.models.User;
import blog.services.NotificationService;
import blog.services.NotificationServiceImpl;
import blog.services.PasswordService;
import blog.services.UserService;
import org.apache.tomcat.jni.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    PasswordService passwordService;

    @RequestMapping("users/register")
    public String registration(RegistrationForm registrationForm) {
        return "users/register";
    }

    @RequestMapping(value = "users/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String registrationPage(@Valid RegistrationForm registrationForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            notificationService.addErrorMessage("Warning!!! Not correct fill the form");
            return "users/register";
        }
        User user = new User(registrationForm.getUsername(), registrationForm.getFullName());
        user.setPasswordHash(passwordService.hash(registrationForm.getPassword().toCharArray()));
        userService.create(user);
        notificationService.addInfoMessage("Register succesfull!!!!");
        return "redirect:/";
    }
}

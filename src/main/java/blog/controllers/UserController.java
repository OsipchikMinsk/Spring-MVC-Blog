package blog.controllers;


import blog.models.Post;
import blog.models.User;
import blog.services.NotificationService;
import blog.services.PostService;
import blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.Notification;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    PostService postService;

    @RequestMapping ("users/userslist")
    public String postsList(Model model){
        List<User> users = userService.findeAll();
    model.addAttribute("users", users);
    if (users.isEmpty()){
        notificationService.addErrorMessage("Users not found");
        return "redirect:/";
    }
        return "users/userslist";
    }
    @RequestMapping("/posts/userposts/{id}")
    public String userPosts(@PathVariable("id") Long id, Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        if (posts == null) {
            notificationService.addErrorMessage("Posts not found");
            return "redirect:/";
        }
        return "posts/userposts";
    }


}

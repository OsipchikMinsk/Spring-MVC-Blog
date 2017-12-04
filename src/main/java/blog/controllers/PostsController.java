package blog.controllers;

import blog.forms.FormCreationPost;
import blog.forms.LoginForm;
import blog.forms.RegistrationForm;
import blog.models.Post;
import blog.models.User;
import blog.services.LoginService;
import blog.services.NotificationService;
import blog.services.PostService;
import blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    private PostService postService;

    @Autowired
    LoginService loginService;

    @Autowired
    private NotificationService notifyService;

    @Autowired
    UserService userService;



    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") long id, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("NOT FouND POST #" + id);
            return "redirect:/";
        }
        model.addAttribute("post", post);
        return "posts/view";
    }

    @RequestMapping("/posts/postslist")
    public String postsList(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        if (posts != null)
            return "posts/postslist";
        notifyService.addErrorMessage("Post not foung");
        return "redirect:/";
    }

    @RequestMapping("/posts/create")
    public String createPost(@Valid FormCreationPost formCreationPost, BindingResult bindingResult) {
        if (formCreationPost.getTitlePost() != null && formCreationPost.getTextPost() != null) {
            Post post = new Post(formCreationPost.getTitlePost(), formCreationPost.getTextPost(), loginService.getUser());
            postService.create(post);

        }
        if (loginService.getUser() == null) {
            notifyService.addErrorMessage("Sorry! Unregistered user");
            return "redirect:/";
        }
//        if (bindingResult.hasErrors()) {
//            notifyService.addErrorMessage("Empty post");
//        }

        return "posts/create";

    }

    @RequestMapping(value = "posts/delete/{id}")
    public String deletePost(@PathVariable("id") Long id, LoginForm loginForm ) {
        Post post = postService.findById(id);

        if (loginService.getUser() == null) {
            notifyService.addErrorMessage("Sorry! Unregistered user");
            return "redirect:/";
        }
        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }
//        if (post.getAuthor().getUsername() != loginService.getUser().getUsername()) {
//            notifyService.addErrorMessage("You can not delete this pos");
//            return "redirect:/";
//        }

       // if (post.getAuthor().getUsername() == String.valueOf(postService.findByAuthor(loginForm.getUsername())))
        postService.deleteById(id);
        return "posts/delete";

    }
    @RequestMapping("/posts/edit/{id}")
    public String editPost(@Valid FormCreationPost formCreationPost
            ,BindingResult bindingResult, @PathVariable("id") Long id) {
        if (loginService.getUser() == null) {
            notifyService.addErrorMessage("Sorry! Unregistered user");
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Empty post");
            return "posts/edit";
        }


        postService.deleteById(id);
        // if (formCreationPost.getTitlePost() != null && formCreationPost.getTextPost() != null) {
        Post post = new Post(id, formCreationPost.getTitlePost(),
                formCreationPost.getTextPost(), loginService.getUser());
        postService.edit(post);

        return "posts/postslist";
    }
}
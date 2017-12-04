package blog.services;

import blog.models.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

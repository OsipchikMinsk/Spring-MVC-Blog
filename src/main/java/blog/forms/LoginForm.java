package blog.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
    @Size(min = 2, max = 30,message = "Enter min 2, max 30 symbols ")
    private  String username;

    @NotNull
    @Size (min = 4, max = 50, message = "Enter min4, max 50 symbols ")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

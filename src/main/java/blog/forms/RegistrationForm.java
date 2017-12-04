package blog.forms;

import blog.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationForm extends LoginForm {

    @NotNull
    @Size(min = 2, max = 30, message = "Enter min 2, max 30 symbols")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

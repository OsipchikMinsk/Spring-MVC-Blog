package blog.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormCreationPost {

    @NotNull
    @Size(min = 2, max = 30, message = "entre min 2 max 30 symbols")
    private String titlePost;

    @NotNull
    private String textPost;

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }
}

package blog.services;

import org.springframework.stereotype.Service;



public interface NotificationService {

    void addInfoMessage(String msg);

    void addErrorMessage(String msg);
}

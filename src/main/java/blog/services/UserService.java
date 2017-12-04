package blog.services;

import blog.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {


    List<User> findeAll();
    User findById (Long id);
    User create (User user);
    User edit (User user);
    void deleteDyId(Long id);

    Long findByName (String userName);


}

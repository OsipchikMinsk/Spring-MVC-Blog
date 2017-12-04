package blog.services;

import blog.models.User;
import blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary

public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public List<User> findeAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteDyId(Long id) {
        this.userRepository.delete(id);
    }

    @Override
    public Long findByName(String username) {
        return this.userRepository.findByUsername(username).getId();
    }
}

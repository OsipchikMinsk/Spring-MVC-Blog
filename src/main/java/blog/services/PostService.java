package blog.services;

import blog.models.Post;
import org.springframework.stereotype.Service;
;

import java.util.List;



public interface PostService {
    List<Post> findAll();
    List<Post> findLatest5();
    Post findById(Long id);
    Post create(Post post);
    Post edit(Post post);
    void deleteById(Long id);
    Post findByAuthor (String author);
}

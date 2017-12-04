package blog.services;

import blog.models.Post;
import blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

/**
 *
 */
@Service
@Primary
public class PostServiceJpaImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public List<Post> findLatest5() {
        return this.postRepository.findLatest5Posts( new PageRequest(0,5));
    }


    @Override
    public Post findById(Long id) {
        return this.postRepository.findOne(id);
    }

    @Override
    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post edit(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
this.postRepository.delete(id);
    }

    @Override
    public Post findByAuthor(String author){
        return this.postRepository.findByAuthor(author);}
}

package blog.repositories;


import blog.models.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author ORDER BY p.date DESC ")
    List<Post> findLatest5Posts(Pageable pageable);

    @Query(value = "SELECT * FROM POSTS WHERE author = ?1", nativeQuery = true)
    Post findByAuthor(String author);

    @Query(value = "SELECT *FROM Post WHERE id =?1", nativeQuery = true)
    Post findById(long id);

    @Query(value = "UPDATE Post p SET p.title =?1, p.body = ?2", nativeQuery = true)
     Post edit(Post post);


}
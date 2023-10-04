package xyz.pintobean.discussify;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, UUID> {
    
    // Find all, ordered by post_date
    public List<Post> findByOrderByPostDate();

    // Find posts that contain a string in title
    public List<Post> findByTitleContainingIgnoreCase(String searchTerm);
}

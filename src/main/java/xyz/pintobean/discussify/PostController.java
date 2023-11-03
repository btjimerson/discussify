package xyz.pintobean.discussify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.apachecommons.CommonsLog;

@Controller
@CommonsLog
public class PostController {

    @Autowired
    PostRepository postRepository;
    
    //Gets all posts
    @GetMapping({"/", "/index"})
    public String getAllPosts(Model model) {

        List<Post> posts = postRepository.findByOrderByPostDate();
        log.debug(String.format("All posts: [%s]", posts));
        model.addAttribute("posts", posts);
        model.addAttribute("tableLabel", "All Posts");
        return "index";
    }

    //Gets a post by id
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") UUID id, Model model) {

        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
                log.error( String.format("No post found with ID '%s'.", id));
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    String.format("No post found with ID '%s'.", id));
        }

        model.addAttribute("post", post.get());        
        model.addAttribute("comment", new Comment());

        return "viewPost";
    }

    //Adds a comment to a post
    @PostMapping("/posts/{id}/comments")
    public String addCommentToPost(Comment comment, @PathVariable("id") UUID postId, Model model) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
                log.error( String.format("No post found with ID '%s'.", postId));
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    String.format("No post found with ID '%s'.", postId));
        }

        Post post = optionalPost.get();

        if (post.getComments() == null) {
            post.setComments(new ArrayList<Comment>());
        }
        comment.setCommentDate(new Date());
        post.getComments().add(comment);
        post = postRepository.save(post);

        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "viewPost";
    }

    //Gets a new post
    @GetMapping("/posts/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "newPost";
    }

    //Saves a new post
    @PostMapping("/posts")
    public String savePost(Post post, Model model) {

        post = postRepository.save(post);
        String message = String.format("Successfully saved post '%s'", post.getTitle());
        List<Post> posts = postRepository.findByOrderByPostDate();
        model.addAttribute("posts", posts);
        model.addAttribute("tableLabel", "All Posts");
        model.addAttribute("message", message);
        return "index";
    }

    //Searches for a search term
    @PostMapping("/search")
    public String search(@RequestParam("searchTerm") String searchTerm, Model model) {

        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(searchTerm);
        log.debug(String.format("Posts found in search for '%s': [%s]", searchTerm, posts));
        model.addAttribute("posts", posts);
        model.addAttribute("tableLabel", String.format("Search results for '%s'", searchTerm));
        return "index";
    }
}

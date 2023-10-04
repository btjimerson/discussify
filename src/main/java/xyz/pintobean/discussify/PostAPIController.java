package xyz.pintobean.discussify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/api/v1")
public class PostAPIController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public @ResponseBody List<Post> getAllPosts() {

        Iterable<Post> posts = postRepository.findAll();
        var postList = new ArrayList<Post>();
        posts.forEach((p) -> {
            postList.add(p);
        });
        log.info(String.format("All posts: [%s]", postList));
        return postList;
    }

    @PostMapping("/posts")
    public @ResponseBody Post savePost(@RequestBody Post post) {

        post = postRepository.save(post);
        log.info(String.format("Saved post [%s]", post));
        return post;

    }

    @PostMapping("/posts/{id}/comments")
    public @ResponseBody Post addCommentToPost(@PathVariable("id") UUID postId, 
        @RequestBody Comment comment) {

            Optional<Post> optionalPost = postRepository.findById(postId);
            if (optionalPost.isEmpty()) {
                log.error( String.format("No post found with ID '%d'.", postId));
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    String.format("No post found with ID '%d'.", postId));
            }

            Post post = optionalPost.get();
            post.getComments().add(comment);
            postRepository.save(post);
            return post;
    }
}

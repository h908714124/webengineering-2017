package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.user.User;
import com.micromata.webengineering.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public Iterable<Post> getPosts() {
        return repository.findAll();
    }


    // Temporary --------------------------------------------------------------------------------------------------
    @Autowired
    private UserRepository userRepository;

    /**
     * Add a new post.
     *
     * @param post the post to add
     */
    public void addPost(Post post) {
        // Option 1: validating the title length is driven by a functional requirement.
        // if (post.getTitle() != null && post.getTitle().length() > 1024) {
        //     throw new IllegalArgumentException("Post title too long");
        // }
        User author = userRepository.findByEmail("mlesniak@micromata.de");
        post.setAuthor(author);

        repository.save(post);
    }

    /**
     * Retrieve a single post.
     *
     * @param id post id
     * @return post with the id or null if no post is found
     */
    public Post getPost(Long id) {
        return repository.findOne(id);
    }

    /**
     * Remove a single post.
     *
     * @param id the post's id.
     */
    public void deletePost(Long id) {
        repository.delete(id);
    }
}

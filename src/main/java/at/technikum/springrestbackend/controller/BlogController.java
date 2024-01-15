package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Blog;
import at.technikum.springrestbackend.service.BlogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    // Create a new user
    @PostMapping("/")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    // Get a specific blog post by ID
    @GetMapping("/")
    public ResponseEntity<List<Blog>> getLatestBlogs() {
        return blogService.getLatestBlogs();
    }

    // Get a specific blog post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable UUID id) {
        return blogService.getBlog(id);
    }

    // Update a specific blog post by ID
    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Blog> updateBlog(@PathVariable UUID id, @RequestBody Blog updatedBlog) {
        return blogService.updateBlog(id, updatedBlog);
    }

    // Delete a specific blog post by ID
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBlog(@PathVariable UUID id) {
        return blogService.deleteBlog(id);
    }
}

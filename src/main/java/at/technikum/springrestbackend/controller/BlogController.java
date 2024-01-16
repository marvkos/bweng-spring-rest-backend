package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Blog;
import at.technikum.springrestbackend.service.BlogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    @GetMapping("/")
    public ResponseEntity<List<Blog>> getLatestBlogs() {
        return blogService.getLatestBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable UUID id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Blog> updateBlog(@PathVariable UUID id, @RequestBody Blog updatedBlog) {
        return blogService.updateBlog(id, updatedBlog);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBlog(@PathVariable UUID id) {
        return blogService.deleteBlog(id);
    }
}

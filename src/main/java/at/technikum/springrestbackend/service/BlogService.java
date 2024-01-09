package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.Blog;
import at.technikum.springrestbackend.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public ResponseEntity<Blog> createBlog(Blog blog){
        blog.setId(UUID.randomUUID());
        return ResponseEntity.ok(blogRepository.save(blog));
    }

    public ResponseEntity<List<Blog>> getLatestBlogs() {
        List<Blog> latestBlogs = blogRepository.findFirst4ByOrderByCreatedOnDesc();
        return ResponseEntity.ok(latestBlogs);
    }

    public ResponseEntity<Blog> getBlog(UUID id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        return blogOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Blog> updateBlog(UUID id, Blog updatedBlog) {
        Optional<Blog> existingBlogOptional = blogRepository.findById(id);
        if (existingBlogOptional.isPresent()) {
            Blog existingBlog = existingBlogOptional.get();

            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setContent(updatedBlog.getContent());
            existingBlog.setAuthor(updatedBlog.getAuthor());

            return ResponseEntity.ok(blogRepository.save(existingBlog));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteBlog(UUID id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return ResponseEntity.ok("Blog deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

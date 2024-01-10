package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Blog;
import at.technikum.springrestbackend.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BlogControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlogService blogService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void createBlog_shouldReturnCreated() throws Exception {
        // Arrange
        Blog newBlog = new Blog("LOL", "Nice Content", "Mister Mister");
        when(blogService.createBlog(any(Blog.class))).thenReturn(new ResponseEntity<>(newBlog, HttpStatus.OK));

        // Act and Assert
        mvc.perform(post("/api/blog/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newBlog)))
                .andExpect(status().is2xxSuccessful());
    }

}

package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    void createUser_shouldReturnCreated() throws Exception {
        // Arrange
        User newUser = new User();
        when(userService.createUser(any(User.class))).thenReturn(new ResponseEntity<>(newUser, HttpStatus.CREATED));

        // Act and Assert
        mvc.perform(post("/api/users/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllUsers_shouldReturnListOfUsers() throws Exception {
        // Arrange
        List<User> users = Collections.singletonList(new User());
        when(userService.getAllUsers()).thenReturn(new ResponseEntity<>(users, HttpStatus.OK));

        // Act and Assert
        mvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getUserById_shouldReturnUserById() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        when(userService.getUserById(userId)).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        // Act and Assert
        mvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteUser_shouldReturnNoContent() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userService.deleteUser(userId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        // Act and Assert
        mvc.perform(delete("/api/users/{id}", userId))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateUser_shouldReturnUpdatedUser() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        User updatedUser = new User();
        updatedUser.setId(userId);
        when(userService.updateUser(eq(userId), any(User.class))).thenReturn(new ResponseEntity<>(updatedUser, HttpStatus.OK));

        // Act and Assert
        mvc.perform(put("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateUsername_shouldReturnUpdatedUser() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        User updatedUser = new User();
        updatedUser.setId(userId);
        when(userService.updateUsername(eq(userId), any(User.class))).thenReturn(updatedUser);

        // Act and Assert
        mvc.perform(patch("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));
    }
}

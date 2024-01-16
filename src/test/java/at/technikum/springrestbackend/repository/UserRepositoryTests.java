package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserRepositoryTests {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUsername() {
        User mockUser = new User();
        mockUser.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        User user = userRepository.findByUsername("testUser");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());

        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    public void testFindByRole() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userRepository.findByRole("USER")).thenReturn(mockUsers);

        List<User> users = userRepository.findByRole("USER");
        assertNotNull(users);
        assertEquals(2, users.size());

        verify(userRepository, times(1)).findByRole("USER");
    }
    @Test
    public void testFindByFirstname() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userRepository.findByFirstname("Alice")).thenReturn(mockUsers);

        List<User> users = userRepository.findByFirstname("Alice");
        assertNotNull(users);
        assertEquals(2, users.size());

        verify(userRepository, times(1)).findByFirstname("Alice");
    }

    @Test
    public void testFindByLastname() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userRepository.findByLastname("Smith")).thenReturn(mockUsers);

        List<User> users = userRepository.findByLastname("Smith");
        assertNotNull(users);
        assertEquals(2, users.size());

        verify(userRepository, times(1)).findByLastname("Smith");
    }

    @Test
    public void testFindByEmail() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        User user = userRepository.findByEmail("test@example.com");
        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());

        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testExistsByEmail() {
        when(userRepository.existsByEmail("exist@example.com")).thenReturn(true);
        when(userRepository.existsByEmail("nonexist@example.com")).thenReturn(false);

        assertTrue(userRepository.existsByEmail("exist@example.com"));
        assertFalse(userRepository.existsByEmail("nonexist@example.com"));

        verify(userRepository, times(1)).existsByEmail("exist@example.com");
        verify(userRepository, times(1)).existsByEmail("nonexist@example.com");
    }

    @Test
    public void testDeleteUserById() {
        doNothing().when(userRepository).deleteUserById(any(UUID.class));

        UUID userId = UUID.randomUUID();
        userRepository.deleteUserById(userId);

        verify(userRepository, times(1)).deleteUserById(userId);
    }


}


package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.repository.GeneralAvailabilityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeneralAvailabilityServiceTest {

    @Mock
    private GeneralAvailabilityRepository generalAvailabilityRepository;

    @InjectMocks
    private GeneralAvailabilityService generalAvailabilityService;


    @Test
    void getAllAvailabilities_shouldReturnListOfAvailabilities() {
        // Arrange
        List<GeneralAvailability> expectedAvailabilities = Arrays.asList(
                new GeneralAvailability(),
                new GeneralAvailability()
        );

        // Mock behavior
        when(generalAvailabilityRepository.findAll()).thenReturn(expectedAvailabilities);

        // Act
        ResponseEntity<List<GeneralAvailability>> response = generalAvailabilityService.getAllAvailabilities();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedAvailabilities, response.getBody());
    }

    @Test
    void getById_shouldReturnAvailabilityById() {
        // Arrange
        UUID availabilityId = UUID.randomUUID();
        GeneralAvailability expectedAvailability = new GeneralAvailability();

        // Mock behavior
        when(generalAvailabilityRepository.findById(availabilityId)).thenReturn(Optional.of(expectedAvailability));

        // Act
        ResponseEntity<GeneralAvailability> response = generalAvailabilityService.getById(availabilityId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedAvailability, response.getBody());
    }

    @Test
    void getById_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        // Mock behavior
        when(generalAvailabilityRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<GeneralAvailability> response = generalAvailabilityService.getById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void create_shouldReturnCreatedAvailability() {
        // Arrange
        GeneralAvailability availabilityToCreate = new GeneralAvailability();

        // Mock behavior
        when(generalAvailabilityRepository.save(any(GeneralAvailability.class))).thenReturn(availabilityToCreate);

        // Act
        ResponseEntity<GeneralAvailability> response = generalAvailabilityService.create(availabilityToCreate);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(availabilityToCreate, response.getBody());
    }

    @Test
    void update_shouldReturnUpdatedAvailability() {
        // Arrange
        UUID availabilityId = UUID.randomUUID();
        GeneralAvailability updatedAvailability = new GeneralAvailability();

        // Mock behavior
        when(generalAvailabilityRepository.existsById(availabilityId)).thenReturn(true);
        when(generalAvailabilityRepository.save(any(GeneralAvailability.class))).thenReturn(updatedAvailability);

        // Act
        ResponseEntity<GeneralAvailability> response = generalAvailabilityService.update(availabilityId, updatedAvailability);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedAvailability, response.getBody());
    }

    @Test
    void update_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();
        GeneralAvailability updatedAvailability = new GeneralAvailability();

        // Mock behavior
        when(generalAvailabilityRepository.existsById(nonExistingId)).thenReturn(false);

        // Act
        ResponseEntity<GeneralAvailability> response = generalAvailabilityService.update(nonExistingId, updatedAvailability);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void delete_shouldReturnNoContentForExistingId() {
        // Arrange
        UUID existingId = UUID.randomUUID();

        // Mock behavior
        when(generalAvailabilityRepository.existsById(existingId)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = generalAvailabilityService.delete(existingId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void delete_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        // Mock behavior
        when(generalAvailabilityRepository.existsById(nonExistingId)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = generalAvailabilityService.delete(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}

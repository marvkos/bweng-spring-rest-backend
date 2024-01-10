package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.SpecificAvailability;
import at.technikum.springrestbackend.repository.SpecificAvailabilityRepository;
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
public class SpecificAvailabilityServiceTest {

    @Mock
    private SpecificAvailabilityRepository specificAvailabilityRepository;

    @InjectMocks
    private SpecificAvailabilityService specificAvailabilityService;


    @Test
    void getAllAvailabilities_shouldReturnListOfAvailabilities() {
        // Arrange
        List<SpecificAvailability> expectedAvailabilities = Arrays.asList(
                new SpecificAvailability(),
                new SpecificAvailability()
        );

        // Mock behavior
        when(specificAvailabilityRepository.findAll()).thenReturn(expectedAvailabilities);

        // Act
        ResponseEntity<List<SpecificAvailability>> response = specificAvailabilityService.getAllAvailabilities();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedAvailabilities, response.getBody());
    }

    @Test
    void getAvailabilityById_shouldReturnAvailabilityById() {
        // Arrange
        UUID availabilityId = UUID.randomUUID();
        SpecificAvailability expectedAvailability = new SpecificAvailability();

        // Mock behavior
        when(specificAvailabilityRepository.findById(availabilityId)).thenReturn(Optional.of(expectedAvailability));

        // Act
        ResponseEntity<SpecificAvailability> response = specificAvailabilityService.getAvailabilityById(availabilityId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedAvailability, response.getBody());
    }

    @Test
    void getAvailabilityById_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        // Mock behavior
        when(specificAvailabilityRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<SpecificAvailability> response = specificAvailabilityService.getAvailabilityById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createAvailability_shouldReturnCreatedAvailability() {
        // Arrange
        SpecificAvailability availabilityToCreate = new SpecificAvailability();

        // Mock behavior
        when(specificAvailabilityRepository.save(any(SpecificAvailability.class))).thenReturn(availabilityToCreate);

        // Act
        ResponseEntity<SpecificAvailability> response = specificAvailabilityService.createAvailability(availabilityToCreate);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(availabilityToCreate, response.getBody());
    }

    @Test
    void updateAvailability_shouldReturnUpdatedAvailability() {
        // Arrange
        UUID availabilityId = UUID.randomUUID();
        SpecificAvailability updatedAvailability = new SpecificAvailability();

        // Mock behavior
        when(specificAvailabilityRepository.existsById(availabilityId)).thenReturn(true);
        when(specificAvailabilityRepository.save(any(SpecificAvailability.class))).thenReturn(updatedAvailability);

        // Act
        ResponseEntity<SpecificAvailability> response = specificAvailabilityService.updateAvailability(availabilityId, updatedAvailability);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedAvailability, response.getBody());
    }

    @Test
    void updateAvailability_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();
        SpecificAvailability updatedAvailability = new SpecificAvailability();

        // Mock behavior
        when(specificAvailabilityRepository.existsById(nonExistingId)).thenReturn(false);

        // Act
        ResponseEntity<SpecificAvailability> response = specificAvailabilityService.updateAvailability(nonExistingId, updatedAvailability);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteAvailability_shouldReturnNoContentForExistingId() {
        // Arrange
        UUID existingId = UUID.randomUUID();

        // Mock behavior
        when(specificAvailabilityRepository.existsById(existingId)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = specificAvailabilityService.deleteAvailability(existingId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteAvailability_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        // Mock behavior
        when(specificAvailabilityRepository.existsById(nonExistingId)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = specificAvailabilityService.deleteAvailability(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}

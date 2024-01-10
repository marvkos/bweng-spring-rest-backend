package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.Lawyer;
import at.technikum.springrestbackend.repository.LawyerRepository;
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
public class LawyerServiceTest {

    @Mock
    private LawyerRepository lawyerRepository;

    @InjectMocks
    private LawyerService lawyerService;

    @Test
    void createLawyer_shouldReturnCreatedLawyer() {
        // Arrange
        Lawyer lawyerToCreate = new Lawyer();

        // Mock behavior
        when(lawyerRepository.save(any(Lawyer.class))).thenReturn(lawyerToCreate);

        // Act
        ResponseEntity<Lawyer> response = lawyerService.createLawyer(lawyerToCreate);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(lawyerToCreate, response.getBody());
    }

    @Test
    void getAllLawyers_shouldReturnListOfLawyers() {
        // Arrange
        List<Lawyer> expectedLawyers = Arrays.asList(
                new Lawyer(),
                new Lawyer()
        );

        // Mock behavior
        when(lawyerRepository.findAll()).thenReturn(expectedLawyers);

        // Act
        ResponseEntity<List<Lawyer>> response = lawyerService.getAllLawyers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedLawyers, response.getBody());
    }

    @Test
    void getLawyerById_shouldReturnLawyerById() {
        // Arrange
        UUID lawyerId = UUID.randomUUID();
        Lawyer expectedLawyer = new Lawyer();

        // Mock behavior
        when(lawyerRepository.findById(lawyerId)).thenReturn(Optional.of(expectedLawyer));

        // Act
        ResponseEntity<Lawyer> response = lawyerService.getLawyerById(lawyerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedLawyer, response.getBody());
    }

    @Test
    void getLawyerById_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        // Mock behavior
        when(lawyerRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Lawyer> response = lawyerService.getLawyerById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateLawyer_shouldReturnUpdatedLawyer() {
        // Arrange
        UUID lawyerId = UUID.randomUUID();
        Lawyer updatedLawyer = new Lawyer();

        // Mock behavior
        when(lawyerRepository.existsById(lawyerId)).thenReturn(true);
        when(lawyerRepository.save(any(Lawyer.class))).thenReturn(updatedLawyer);

        // Act
        ResponseEntity<Lawyer> response = lawyerService.updateLawyer(lawyerId, updatedLawyer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedLawyer, response.getBody());
    }

    @Test
    void updateLawyer_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();
        Lawyer updatedLawyer = new Lawyer();

        // Mock behavior
        when(lawyerRepository.existsById(nonExistingId)).thenReturn(false);

        // Act
        ResponseEntity<Lawyer> response = lawyerService.updateLawyer(nonExistingId, updatedLawyer);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteLawyer_shouldReturnNoContentForExistingId() {
        // Arrange
        UUID existingId = UUID.randomUUID();

        // Mock behavior
        when(lawyerRepository.existsById(existingId)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = lawyerService.deleteLawyer(existingId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteLawyer_shouldReturnNotFoundForNonExistingId() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        // Mock behavior
        when(lawyerRepository.existsById(nonExistingId)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = lawyerService.deleteLawyer(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}

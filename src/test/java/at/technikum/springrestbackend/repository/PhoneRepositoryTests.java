package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.model.Phone;
import at.technikum.springrestbackend.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PhoneRepositoryTests {

    @Mock
    private PhoneRepository phoneRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByName() {
        List<Phone> mockPhones = Arrays.asList(new Phone(), new Phone());
        when(phoneRepository.findByName("iPhone")).thenReturn(mockPhones);

        List<Phone> phones = phoneRepository.findByName("iPhone");
        assertNotNull(phones);
        assertEquals(2, phones.size());

        verify(phoneRepository, times(1)).findByName("iPhone");
    }

    @Test
    public void testFindByDisplaySize() {
        List<Phone> mockPhones = Arrays.asList(new Phone(), new Phone());
        when(phoneRepository.findByDisplaySize(6.0f)).thenReturn(mockPhones);

        List<Phone> phones = phoneRepository.findByDisplaySize(6.0f);
        assertNotNull(phones);
        assertEquals(2, phones.size());

        verify(phoneRepository, times(1)).findByDisplaySize(6.0f);
    }
}

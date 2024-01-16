package at.technikum.springrestbackend.util;
import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.service.BrandService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BrandValidatorTest {

    @Mock
    private BrandService brandService;

    @InjectMocks
    private BrandValidator brandValidator;

    @Test
    public void testValidateBrand_WhenBrandIsNotTaken() {
        // Arrange
        Brand brand = new Brand();
        brand.setName("NewBrand");

        when(brandService.isBrandTaken("NewBrand")).thenReturn(false);

        // Act
        List<String> validationErrors = brandValidator.validateBrand(brand);

        // Assert
        assertEquals(Collections.emptyList(), validationErrors, "Expected no validation errors");
        verify(brandService, times(1)).isBrandTaken("NewBrand");
    }

    @Test
    public void testValidateBrand_WhenBrandIsTaken() {
        // Arrange
        Brand brand = new Brand();
        brand.setName("ExistingBrand");

        when(brandService.isBrandTaken("ExistingBrand")).thenReturn(true);

        // Act
        List<String> validationErrors = brandValidator.validateBrand(brand);

        // Assert
        assertEquals(Collections.singletonList("Brand already exists"), validationErrors, "Expected validation error");
        verify(brandService, times(1)).isBrandTaken("ExistingBrand");
    }
}
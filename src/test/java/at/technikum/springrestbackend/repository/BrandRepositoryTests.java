package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BrandRepositoryTests {
    @Mock
    private BrandRepository brandRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByname() {
        Brand mockBrand = new Brand();
        mockBrand.setName("testBrand");
        when(brandRepository.findByname("testBrand")).thenReturn(mockBrand);

        Brand brand = brandRepository.findByname("testBrand");
        assertNotNull(brand);
        assertEquals("testBrand", brand.getName());

        verify(brandRepository, times(1)).findByname("testBrand");
    }

    @Test
    public void testFindAll() {
        List<Brand> mockBrands = Arrays.asList(new Brand(), new Brand());
        when(brandRepository.findAll()).thenReturn(mockBrands);

        List<Brand> brands = brandRepository.findAll();
        assertNotNull(brands);
        assertEquals(2, brands.size());

        verify(brandRepository, times(1)).findAll();
    }

    @Test
    public void testSaveBrand() {
        Brand mockBrand = new Brand();
        mockBrand.setName("newBrand");
        when(brandRepository.save(any(Brand.class))).thenReturn(mockBrand);

        Brand savedBrand = brandRepository.save(mockBrand);
        assertNotNull(savedBrand);
        assertEquals("newBrand", savedBrand.getName());

        verify(brandRepository, times(1)).save(any(Brand.class));
    }

    @Test
    public void testExistsByname() {
        when(brandRepository.existsByname("existingBrand")).thenReturn(true);
        when(brandRepository.existsByname("nonExistingBrand")).thenReturn(false);

        assertTrue(brandRepository.existsByname("existingBrand"));
        assertFalse(brandRepository.existsByname("nonExistingBrand"));

        verify(brandRepository, times(1)).existsByname("existingBrand");
        verify(brandRepository, times(1)).existsByname("nonExistingBrand");
    }

    @Test
    public void testDeleteBrandByname() {
        doNothing().when(brandRepository).deleteBrandByname("toBeDeletedBrand");

        brandRepository.deleteBrandByname("toBeDeletedBrand");

        verify(brandRepository, times(1)).deleteBrandByname("toBeDeletedBrand");
    }

    @Test
    public void testUpdateBrandInfo() {
        when(brandRepository.updateBrandInfo("oldBrand", "newBrand", "newPath")).thenReturn(1);

        int updatedCount = brandRepository.updateBrandInfo("oldBrand", "newBrand", "newPath");
        assertEquals(1, updatedCount);

        verify(brandRepository, times(1)).updateBrandInfo("oldBrand", "newBrand", "newPath");
    }
}

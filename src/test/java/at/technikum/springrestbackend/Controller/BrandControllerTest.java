package at.technikum.springrestbackend.Controller;
import at.technikum.springrestbackend.controller.BrandController;
import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.BrandService;
import at.technikum.springrestbackend.service.UserService;
import at.technikum.springrestbackend.util.BrandValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class BrandControllerTest {

    @Mock
    private BrandService brandService;
    @Mock
    private UserService userService;

    @Mock
    private BrandValidator brandValidator;

    @InjectMocks
    private BrandController brandController;

    private MockMvc mockMvc;

@BeforeEach
void setUp(){
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
}



    @Test
     void testGetBrands() {
        // Arrange
        List<Brand> mockBrands = Arrays.asList(
                new Brand( "Brand1", "noPicutre", new User("john_doe","P@ssw0rd", "ROLE_user", "john", "doe", "Mr","john@example.com", null, true, "Am lange Felde", "Wien", 1220, "59/2/3","AT")),
                new Brand("Brand2", "noPicture", new User("john_doe","P@ssw0rd", "ROLE_user", "john", "doe", "Mr","john@example.com", null, true, "Am lange Felde", "Wien", 1220, "59/2/3","AT"))
                // Add more brands as needed
        );

        when(brandService.getBrands()).thenReturn(mockBrands);

        List<Brand> result = brandController.getBrands();

        assertEquals(mockBrands, result);
    }

    @Test
    void testGetBrandByName() throws Exception {
        // Arrange
        String brandName = "Brand1";
        Brand mockBrand = new Brand(brandName, "noPicture", new User("john_doe", "P@ssw0rd", "ROLE_user", "john", "doe", "Mr", "john@example.com", null, true, "Am lange Felde", "Wien", 1220, "59/2/3", "AT"));

        when(brandService.getBrandByname(brandName)).thenReturn(mockBrand);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/brand/{name}", brandName))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"name\":\"Brand1\",\"picturePath\":\"noPicture\",\"createdBy\":{\"username\":\"john_doe\"}}"));
    }

    @Test
    void testAddBrand() throws Exception {
        // Arrange
        User user = new User("JohnDoe", "P@ssw0rd", "ROLE_user", "john", "doe", "Mr", "john@example.com", null, true, "Am lange Felde", "Wien", 1220, "59/2/3", "AT");
        Brand brandToAdd = new Brand("Brand3", "noPicture", null);

        when(userService.getUserByUsername("JohnDoe")).thenReturn(user);
        when(brandValidator.validateBrand(brandToAdd)).thenReturn(List.of());
        when(userService.getUser(user.getId())).thenReturn(user);
        when(brandService.createBrand(brandToAdd)).thenReturn(brandToAdd);

        // Act & Assert
        ResponseEntity<Object> result = brandController.addBrand("JohnDoe", brandToAdd);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void testDeleteBrand() throws Exception {
        // Arrange
        String brandName = "Brand1";
        Brand mockBrand = new Brand(brandName, "noPicture", new User("john_doe", "P@ssw0rd", "ROLE_user", "john", "doe", "Mr", "john@example.com", null, true, "Am lange Felde", "Wien", 1220, "59/2/3", "AT"));

        when(brandService.getBrandByname(brandName)).thenReturn(mockBrand);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/deletebrand/{name}", brandName))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBrand() throws Exception {
        // Arrange
        String brandName = "Brand1";
        Brand updatedBrand = new Brand("UpdatedBrand", "newPicture", null);

        when(brandService.updateBrandInfo(eq(brandName), eq(updatedBrand.getName()), eq(updatedBrand.getPicturePath()))).thenReturn(1);

        // Act & Assert
        ResponseEntity<Object> result = brandController.updateBrand(brandName, updatedBrand);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }




}

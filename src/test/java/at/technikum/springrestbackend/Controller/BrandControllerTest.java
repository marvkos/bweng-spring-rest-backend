package at.technikum.springrestbackend.Controller;

import at.technikum.springrestbackend.controller.BrandController;
import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.BrandService;
import at.technikum.springrestbackend.service.UserService;
import at.technikum.springrestbackend.util.BrandValidator;
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


import com.auth0.jwt.exceptions.TokenExpiredException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {

    @Mock
    private BrandService brandService;


    @Mock
    private BrandValidator brandValidator;

    @Mock
    private UserService userService;


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
        List<Brand> mockBrands = Arrays.asList(new Brand("Brand1", "Picture1"), new Brand("Brand2", "Picture2"));
        when(brandService.getBrands()).thenReturn(mockBrands);

        List<Brand> result = brandController.getBrands();

        assertEquals(mockBrands, result);
    }

    @Test
    void testGetBrand_BrandExists() {
        Brand mockBrand = new Brand("Brand1", "Picture1");
        when(brandService.getBrandByname("Brand1")).thenReturn(mockBrand);

        Brand result = brandController.getBrand("Brand1");

        assertEquals(mockBrand, result);
    }

    @Test
    void testGetBrand_BrandNotFound() {
        when(brandService.getBrandByname("NonexistentBrand")).thenReturn(null);

        Brand result = brandController.getBrand("NonexistentBrand");

        assertEquals(null, result);
    }

    @Test
    void testAddBrand_ValidBrand    () {
        when(brandValidator.validateBrand(any(Brand.class))).thenReturn(Collections.emptyList());
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        ResponseEntity<Object> result = brandController.addBrand("john_doe", new Brand("Brand1", "Picture1"));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("New brand is saved.", result.getBody());
    }

    @Test
    void testAddBrand_InvalidBrand() {
        when(brandValidator.validateBrand(any(Brand.class))).thenReturn(Arrays.asList("Error 1", "Error 2"));
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        ResponseEntity<Object> result = brandController.addBrand("john_doe", new Brand("InvalidBrand", ""));

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(Arrays.asList("Error 1", "Error 2"), result.getBody());
    }

    @Test
    void testDeleteBrand_BrandNotFound() {
        when(brandService.getBrandByname("NonexistentBrand")).thenReturn(null);

        ResponseEntity<Object> result = brandController.deleteUser("NonexistentBrand");

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
    @Test
    void testAddBrand_UserNotFound() {
        when(userService.getUserByUsername("nonexistent_user")).thenReturn(null);

        ResponseEntity<Object> result = brandController.addBrand("nonexistent_user", new Brand("BrandX", "PictureX"));

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("No User with that username", result.getBody());
    }

    @Test
    void testAddBrand_TokenExpired() {
        when(userService.getUserByUsername(anyString())).thenReturn(new User());
        when(brandValidator.validateBrand(any(Brand.class))).thenReturn(Collections.emptyList());
        doThrow(new TokenExpiredException("Token expired", Instant.now())).when(brandService).createBrand(any(Brand.class));

        ResponseEntity<Object> result = brandController.addBrand("user", new Brand("BrandX", "PictureX"));

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertEquals("The JWT Token is expired, pleas login in again", result.getBody());
    }
    @Test
    void testDeleteBrand_Successful() {
        Brand brandToDelete = new Brand("ExistingBrand", "Picture");
        when(brandService.getBrandByname("ExistingBrand")).thenReturn(brandToDelete);

        ResponseEntity<Object> result = brandController.deleteUser("ExistingBrand");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Brand deleted successfully", result.getBody());
    }

    @Test
    void testDeleteBrand_Conflict() {
        Brand brandToDelete = new Brand("BrandWithDependencies", "Picture");
        when(brandService.getBrandByname("BrandWithDependencies")).thenReturn(brandToDelete);
        doThrow(new DataIntegrityViolationException("Cannot delete")).when(brandService).deleteBrand("BrandWithDependencies");

        ResponseEntity<Object> result = brandController.deleteUser("BrandWithDependencies");

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals("Cannot delete the brand. It has associated phones.", result.getBody());
    }
    @Test
    void testUpdateBrand_Successful() {
        when(brandService.updateBrandInfo(anyString(), anyString(), anyString())).thenReturn(1);

        ResponseEntity<Object> result = brandController.updateBrand("ExistingBrand", new Brand("UpdatedName", "UpdatedPicture"));

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Brand info has been updated successfully", result.getBody());
    }

    @Test
    void testUpdateBrand_NotFound() {
        when(brandService.updateBrandInfo(anyString(), anyString(), anyString())).thenReturn(0);

        ResponseEntity<Object> result = brandController.updateBrand("NonexistentBrand", new Brand("UpdatedName", "UpdatedPicture"));

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Brand not found", result.getBody());
    }
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


package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.BrandService;
import at.technikum.springrestbackend.util.PasswordValidator;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }
    @GetMapping("/brands")
    public List<Brand> getBrands(){
        return brandService.getBrands();
    }
    @GetMapping("/brands/name")
    public Brand getBrand(String name){
        return brandService.getBrandByname(name);
    }
    @PostMapping("/addbrand")
    public ResponseEntity<Object> addBrand(@RequestBody @Valid Brand brand) {
        List<String> validationErrors = validateBrand(brand);
        if (!validationErrors.isEmpty()) {
            return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        }

        brandService.createBrand(brand);
        return new ResponseEntity<>("New brand is saved.", HttpStatus.CREATED);
    }
    @DeleteMapping("/brands/brandid/{name}")
    public ResponseEntity<Object> deleteBrand(@PathVariable String name) {
        Brand brandToDelete = brandService.getBrandByname(name);

        if (brandToDelete == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        brandService.deleteBrand(name);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
    private List<String> validateBrand(Brand brand) {
        List<String> validationErrors = new ArrayList<>();

        if (brandService.isBrandTaken(brand.getName())) {
            validationErrors.add("Brand is already exists");
        }
        return validationErrors;
    }
}

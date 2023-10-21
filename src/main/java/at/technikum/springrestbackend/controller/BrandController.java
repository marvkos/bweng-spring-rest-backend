package at.technikum.springrestbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/authors")
    public List<Brand> getAuthors() {
        return brandService.getAuthors();
    }

    @GetMapping("/authors/{id}")
    public Brand getAuthor(@PathVariable UUID id) {
        return brandService.getBrand(id);
    }
}

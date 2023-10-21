package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Phone;
import at.technikum.springrestbackend.service.PhoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PhoneController {
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/books")
    public List<Phone> getBooks() {
        return phoneService.getBooks();
    }

    @GetMapping("/books/{id}")
    public Phone getBook(@PathVariable UUID id) {
        return phoneService.getPhone(id);
    }

    @PostMapping("/books")
    public Phone createBook(@RequestBody Phone phone) {
        return phoneService.createBook(phone);
    }
}

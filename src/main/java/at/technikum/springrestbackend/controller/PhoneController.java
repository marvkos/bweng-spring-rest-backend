package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.model.Phone;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PhoneController {
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/phones")
    public List<Phone> getPhones() {
        return phoneService.getPhones();
    }

    @GetMapping("/phones/{id}")
    public Phone getPhone(@PathVariable UUID id) {
        return phoneService.getPhone(id);
    }

    @GetMapping("/phones/{name}")
    public List<Phone> getPhonesName(String name){
        return phoneService.getPhonesName(name);
    }
    @GetMapping("/phones/{display}")
    public List<Phone> getPhoneDisplay(float displaysize){
        return phoneService.getPhonesDisplay(displaysize);
    }
    @GetMapping("/phones/{memory}")
    public List<Phone> getPhonesMemory(int memory){
        return phoneService.getPhonesMemory(memory);
    }
    @GetMapping("/phones/{battery}")
    public List<Phone> getPhonesBattery(int battery){
        return phoneService.getPhonesBattery(battery);
    }
    @GetMapping("/phones/price")
    public List<Phone> getPhonePrice(float price){
        return phoneService.getPhonesPrice(price);
    }
    @GetMapping("/phones/brand")
    public List<Phone> getPhonesBrand(Brand brand){
        return phoneService.getPhonesBrand(brand);
    }
    @PostMapping("/phones")
    public Phone createPhone(@RequestBody Phone phone) {
        return phoneService.createPhone(phone);
    }
    @DeleteMapping("/phones/{id}")
    public ResponseEntity<String> deletePhone(@PathVariable UUID id) {
        Phone phoneToDelete = phoneService.getPhone(id);

        if (phoneToDelete == null) {
            return new ResponseEntity<>("Phone not found", HttpStatus.NOT_FOUND);
        }

        phoneService.deletePhone(id);

        return new ResponseEntity<>("Phone deleted successfully", HttpStatus.OK);
    }

}

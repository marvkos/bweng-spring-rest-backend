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
    public List<Phone> getPhonesBrand(String brand){
        return phoneService.getPhonesBrand(brand);
    }
    @PostMapping("/phones")
    public Phone createPhone(@RequestBody Phone phone) {
        return phoneService.createPhone(phone);
    }
}
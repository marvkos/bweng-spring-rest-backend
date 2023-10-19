package at.fhtw.be_webeng_23ws.service;

import at.fhtw.be_webeng_23ws.entity.Phone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {

    /*

    Need to rework

    private final BookRepository bookRepository;

    public PhoneService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Phone> getBooks() {
        return bookRepository.findAll();
    }

    public Phone getPhone(UUID id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Phone createBook(Phone phone) {
        return bookRepository.save(phone);
    }

    // visible for testing
    String getEra(int year) {
        if (year < 1900) {
            return "19th century or before";
        } else if (year < 2000) {
            return "20th century";
        } else if (year < 2100) {
            return "21st century";
        } else {
            throw new IllegalArgumentException("Year must not be larger than or equal to 2100");
        }
    }*/
}

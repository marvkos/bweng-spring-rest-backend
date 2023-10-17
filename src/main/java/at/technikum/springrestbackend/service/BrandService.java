package at.fhtw.be_webeng_23ws.service;

import at.fhtw.be_webeng_23ws.entity.Brand;
import at.fhtw.be_webeng_23ws.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BrandService {

    private final AuthorRepository authorRepository;

    public BrandService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Brand> getAuthors() {
        return authorRepository.findAll();
    }

    public Brand getBrand(UUID id) {
        return authorRepository.findById(id).orElseThrow();
    }
}

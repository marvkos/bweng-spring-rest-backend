package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand getBrand(UUID id){ return brandRepository.findById(id).orElseThrow();}
    public List<Brand> getBrands(){
        return brandRepository.findAll();
    }
    public Brand getBrandByname(String name){
        return brandRepository.findByname(name);
    }
    @Transactional
    public Brand createBrand(Brand brand){
        return brandRepository.save(brand);
    }
    public boolean isBrandTaken(String name) {
        return brandRepository.existsByname(name);
    }
    @Transactional
    public void deleteBrand(String name) { brandRepository.deleteBrandByname(name);}
    @Transactional
    public int updateBrandInfo(String oldName, String newName, String newPicturePath) {
        return brandRepository.updateBrandInfo(oldName,newName, newPicturePath);
    }

}

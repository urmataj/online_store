package com.example.online_store.controller;

import com.example.online_store.entity.ProductEntity;
import com.example.online_store.dto.ProductUpdateDto;
import com.example.online_store.dto.SuccessDto;
import com.example.online_store.repository.ProductRepository;
import com.example.online_store.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/get/{id}")
    public ProductEntity getById(@PathVariable("id") Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product " + id + " not found", HttpStatusCode.valueOf(404)));
    }

    @GetMapping("/get-all")
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @PostMapping("/create")
    public ProductEntity create(@RequestBody ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @PutMapping("/update/{id}")
    public ProductEntity update(@RequestBody ProductUpdateDto productDto, @PathVariable("id") Long id) {
        ProductEntity toUpdate = productRepository.findById(id).get();
        if (productDto.getName() != null) {
            toUpdate.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            toUpdate.setDescription(productDto.getDescription());
        }
        if (productDto.getPrice() != null) {
            toUpdate.setPrice(productDto.getPrice());
        }
        if (productDto.getStockQuantity() != null) {
            toUpdate.setStockQuantity(productDto.getStockQuantity());
        }
        return productRepository.save(toUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public SuccessDto delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new SuccessDto(true);
    }
}


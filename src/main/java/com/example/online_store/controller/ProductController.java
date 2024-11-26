package com.example.online_store.controller;

import com.example.online_store.dto.ProductUpdateDto;
import com.example.online_store.dto.SuccessDto;
import com.example.online_store.entity.ProductEntity;
import com.example.online_store.exception.ApiException;
import com.example.online_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository ProductRepository;


    @GetMapping("get/{id}")
    public ProductEntity getById(@PathVariable("id") Long id){
        return ProductRepository.findById(id).orElseThrow(() -> new ApiException("Product " + id + " not found", HttpStatusCode.valueOf(404)));
    }

    @GetMapping("get-all")
    public List<ProductEntity> getAll() {
        return ProductRepository.findAll();
    }

    @PostMapping("create")
    public ProductEntity create(@RequestBody ProductEntity ProductEntity) {
        return ProductRepository.save(ProductEntity);
    }

    @PutMapping("update/{id}")
    public ProductEntity update(@RequestBody ProductUpdateDto Product, @PathVariable("id") Long id) {
        ProductEntity toUpdate = ProductRepository.findById(id).get();
        if (Product.getName() != null) {
            toUpdate.setName(Product.getName());
        }
        if (Product.getSeller() != null) {
            toUpdate.setSeller(Product.getSeller());
        }
        if (Product.getType() != null) {
            toUpdate.setType(Product.getType());
        }
        toUpdate.setAvailable(Product.getAvailable());
        return ProductRepository.save(toUpdate);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable Long id) {
        ProductRepository.deleteById(id);
        return new SuccessDto(true);
    }

}

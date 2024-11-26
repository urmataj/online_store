package com.example.online_store.controller;

import com.example.online_store.dto.CustomerDto;
import com.example.online_store.entity.CustomerEntity;
import com.example.online_store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/customers/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/get-all")
    public List<CustomerEntity> getAll(@RequestParam(value = "name", required = false) String name) {
        if (name != null){
            return customerRepository.findAllByFullNameContainingIgnoreCase(name);
        }else {
            return customerRepository.findAll();
        }
    }

    @PostMapping("/create")
    public CustomerEntity create(@RequestBody CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @PutMapping("/update/{id}")
    public CustomerEntity update(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        CustomerEntity toUpdate = customerRepository.findById(id).get();
        if (customerDto.getFullName() != null){
            toUpdate.setFullName(customerDto.getFullName());
        }
        if (customerDto.getPhoneNumber() != null) {
            toUpdate.setPhoneNumber(customerDto.getPhoneNumber());
        }
        if (customerDto.getCreditCardNumber() != 0) {
            toUpdate.setCreditCardNumber(customerDto.getCreditCardNumber());
        }

        return customerRepository.save(toUpdate);
    }

}

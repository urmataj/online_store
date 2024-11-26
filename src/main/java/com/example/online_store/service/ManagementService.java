package com.example.online_store.service;

import com.example.online_store.dto.CustomerDto;
import com.example.online_store.dto.ProductCheckOut;
import com.example.online_store.dto.ProductOverDueDto;
import com.example.online_store.dto.ProductReturnDto;
import com.example.online_store.entity.ProductEntity;
import com.example.online_store.entity.CustomerEntity;
import com.example.online_store.exception.ApiException;
import com.example.online_store.repository.ProductRepository;
import com.example.online_store.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagementService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public boolean checkOutUser(ProductCheckOut productCheckOut) {
        ProductEntity product = productRepository.findById(productCheckOut.getProductId()).orElseThrow(() -> new ApiException("Book " + productCheckOut.getProductId() + " not found", HttpStatusCode.valueOf(404)));
        CustomerEntity customer = customerRepository.findById(productCheckOut.getCustomerId()).orElseThrow(() -> new ApiException("Reader " + productCheckOut.getCustomerId() + " not found", HttpStatusCode.valueOf(404)));
        if (product.getCustomer() != null) {
            throw new RuntimeException();
        }
        product.setCustomer(customer);
        product.setReturnDate(LocalDateTime.now().plusDays(productCheckOut.getPeriodDays()));
        productRepository.save(product);
        return true;
    }

    public ProductOverDueDto returnProduct(ProductReturnDto productReturnDto) {
        ProductOverDueDto productOverDueDto = new ProductOverDueDto();
        ProductEntity product = productRepository.findById(productReturnDto.getProductId()).orElseThrow(() -> new ApiException("Book " + productReturnDto.getProductId() + " not found", HttpStatusCode.valueOf(404)));

        Duration overdue = Duration.between(product.getReturnDate(), LocalDateTime.now());

        String overDuePeriod = "Days: " +
                overdue.toDays() +
                ", Hours: " +
                overdue.toHoursPart() +
                ", Minutes: " +
                overdue.toMinutesPart();

        productOverDueDto.setOverduePeriod(overDuePeriod);
        productOverDueDto.setOverdue(LocalDateTime.now().isAfter(product.getReturnDate()));

        product.setReturnDate(null);
        product.setCustomer(null);

        productRepository.save(product);
        return productOverDueDto;
    }
}


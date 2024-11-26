package com.example.online_store.controller;

import jakarta.validation.Valid;
import com.example.online_store.dto.ProductCheckOut;
import com.example.online_store.dto.ProductOverDueDto;
import com.example.online_store.dto.ProductReturnDto;
import com.example.online_store.dto.SuccessDto;
import com.example.online_store.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    @PostMapping("/check-out-Product")
    public SuccessDto checkOutProduct(@RequestBody @Valid ProductCheckOut ProductCheckOut) {
        return new SuccessDto(managementService.checkOutUser(ProductCheckOut));
    }

    @PostMapping("/return-Product")
    public ProductOverDueDto returnProduct(@RequestBody ProductReturnDto ProductReturnDto) {
        return managementService.returnProduct(ProductReturnDto);
    }

}

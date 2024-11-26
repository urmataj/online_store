package com.example.online_store.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private Long id;

    private String fullName;

    private String phoneNumber;

    private int creditCardNumber;

}

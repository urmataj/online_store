package com.example.online_store.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUpdateDto {

    String name;

    String type;

    boolean available;

    String seller;

    public boolean getAvailable() {
        return false;
    }
}


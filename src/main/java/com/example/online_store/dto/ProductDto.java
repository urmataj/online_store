package com.example.online_store.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;

    String name;

    String description;

    Double price;

    Integer stockQuantity;
}

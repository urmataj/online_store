package com.example.online_store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCheckOut {

    @NotNull(message = "customerId is required")
    Long customerId;

    @NotNull(message = "productId is required")
    Long productId;

    @NotNull(message = "periodDays is required")
    Integer periodDays;

}

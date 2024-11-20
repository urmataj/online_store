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
public class OrderCheckOutDto {

    @NotNull(message = "userId is required")
    Long userId;

    @NotNull(message = "orderId is required")
    Long orderId;

    @NotNull(message = "periodDays is required")
    Integer periodDays;

}

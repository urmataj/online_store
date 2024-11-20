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
public class OrderDto {

    Long id;

    Long userId;

    Double totalAmount;

    String status; // Возможные статусы: "Pending", "Shipped", "Completed", "Cancelled"
}


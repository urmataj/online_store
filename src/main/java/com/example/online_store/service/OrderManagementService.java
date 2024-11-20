package com.example.online_store.service;

import com.example.online_store.dto.OrderCheckOutDto;
import com.example.online_store.dto.OrderOverDueDto;
import com.example.online_store.dto.OrderReturnDto;
import com.example.online_store.entity.OrderEntity;
import com.example.online_store.entity.UserEntity;
import com.example.online_store.exception.ApiException;
import com.example.online_store.repository.OrderRepository;
import com.example.online_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderManagementService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public boolean checkOutUser(OrderCheckOutDto orderCheckOutDto) {
        OrderEntity order = orderRepository.findById(orderCheckOutDto.getOrderId()).orElseThrow(() -> new ApiException("Order " + orderCheckOutDto.getOrderId() + " not found", HttpStatusCode.valueOf(404)));
        UserEntity user = userRepository.findById(orderCheckOutDto.getUserId()).orElseThrow(() -> new ApiException("User " + orderCheckOutDto.getUserId() + " not found", HttpStatusCode.valueOf(404)));
        if (order.getUser() != null) {
            throw new RuntimeException("Order is already assigned to a user.");
        }
        order.setUser(user);
        order.setReturnDate(LocalDateTime.now().plusDays(orderCheckOutDto.getPeriodDays()));
        orderRepository.save(order);
        return true;
    }

    public OrderOverDueDto returnOrder(OrderReturnDto orderReturnDto) {
        OrderOverDueDto orderOverDueDto = new OrderOverDueDto();
        OrderEntity order = orderRepository.findById(orderReturnDto.getOrderId()).orElseThrow(() -> new ApiException("Order " + orderReturnDto.getOrderId() + " not found", HttpStatusCode.valueOf(404)));

        Duration overdue = Duration.between(order.getReturnDate(), LocalDateTime.now());

        String overDuePeriod = "Days: " +
                overdue.toDays() +
                ", Hours: " +
                overdue.toHoursPart() +
                ", Minutes: " +
                overdue.toMinutesPart();

        orderOverDueDto.setOverduePeriod(overDuePeriod);
        orderOverDueDto.setOverdue(LocalDateTime.now().isAfter(order.getReturnDate()));

        order.setReturnDate(null);
        order.setUser(null);

        orderRepository.save(order);
        return orderOverDueDto;
    }
}

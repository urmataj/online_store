package com.example.online_store.controller;

import com.example.online_store.entity.OrderEntity;
import com.example.online_store.dto.OrderStatusUpdateDto;
import com.example.online_store.dto.SuccessDto;
import com.example.online_store.repository.OrderRepository;
import com.example.online_store.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/get/{id}")
    public OrderEntity getById(@PathVariable("id") Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("Order " + id + " not found", HttpStatusCode.valueOf(404)));
    }

    @GetMapping("/get-all")
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @PostMapping("/create")
    public OrderEntity create(@RequestBody OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @PutMapping("/update-status/{id}")
    public OrderEntity updateStatus(@RequestBody OrderStatusUpdateDto statusDto, @PathVariable("id") Long id) {
        OrderEntity order = orderRepository.findById(id).get();
        if (statusDto.getStatus() != null) {
            order.setStatus(statusDto.getStatus());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/delete/{id}")
    public SuccessDto delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return new SuccessDto(true);
    }
}

package com.example.online_store.repository;

import com.example.online_store.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUserId(Long userId);

    List<OrderEntity> findAllByStatus(String status);
}

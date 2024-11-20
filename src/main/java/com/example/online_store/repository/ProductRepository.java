package com.example.online_store.repository;

import com.example.online_store.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByNameContainingIgnoreCase(String name);
}

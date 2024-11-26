package com.example.online_store.repository;

import com.example.online_store.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    // Метод для получения всех заказов определенного покупателя
    List<CustomerEntity> findAllByFullNameContainingIgnoreCase(String name);

}

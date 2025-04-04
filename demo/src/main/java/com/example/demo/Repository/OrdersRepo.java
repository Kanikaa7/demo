package com.example.demo.Repository;

import com.example.demo.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders,Integer> {
}

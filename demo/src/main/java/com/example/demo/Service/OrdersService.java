package com.example.demo.Service;

import com.example.demo.Entity.Orders;
import com.example.demo.Repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    OrdersRepo orderRepo;

    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    public Orders saveDetails(Orders order){
        return orderRepo.save(order);
    }
}

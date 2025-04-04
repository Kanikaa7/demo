package com.example.demo.Service;

import com.example.demo.Entity.Orders;
import com.example.demo.Repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    @Autowired
    OrdersRepo orderRepo;

    public Orders saveDetails(Orders order){
        return orderRepo.save(order);
    }
}

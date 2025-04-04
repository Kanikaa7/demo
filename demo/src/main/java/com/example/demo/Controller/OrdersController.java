package com.example.demo.Controller;

import com.example.demo.Entity.Orders;
import com.example.demo.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    OrdersService orderService;

    @PostMapping("/addOrder")
    public Orders postDetails(@RequestBody Orders order){
        return orderService.saveDetails(order);
    }
}

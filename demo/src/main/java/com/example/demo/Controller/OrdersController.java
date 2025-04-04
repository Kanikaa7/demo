package com.example.demo.Controller;

import com.example.demo.Entity.Orders;
import com.example.demo.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class OrdersController {

    @Autowired
    OrdersService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/send-to-python")
    public ResponseEntity<Map<String, Object>> sendDataToPython() {
        List<Orders> orders = orderService.getAllOrders(); // Fetch orders from DB

        String pythonApiUrl = "http://localhost:5000/process-data";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request with headers and body
        HttpEntity<List<Orders>> request = new HttpEntity<>(orders, headers);

        // Call Python API and expect a Map (JSON object)
        ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, request, Map.class);

        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/addOrder")
    public Orders postDetails(@RequestBody Orders order){
        return orderService.saveDetails(order);
    }
}

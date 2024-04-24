package com.example.project4.controller;

import com.example.project4.dto.request.order.AddOrderRequest;
import com.example.project4.dto.response.OrderResponse;
import com.example.project4.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody AddOrderRequest addOrderRequest) {
        OrderResponse responseBody = orderService.createOrder(addOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }


    @DeleteMapping("")
    public ResponseEntity<Void> deleteOrder(@RequestParam Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteOrder2(@RequestParam Integer id) {
        orderService.deleteOrder2(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/delete3")
    public ResponseEntity<Void> deleteOrder3(@RequestParam Integer id) {
        orderService.deleteOrder3(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
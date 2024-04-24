package com.example.project4.service;

import com.example.project4.dto.request.order.AddOrderRequest;
import com.example.project4.dto.request.order.UpdateOrderRequest;
import com.example.project4.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Integer id);

    OrderResponse createOrder(AddOrderRequest addOrderRequest);

    void updateOrder(Integer id, UpdateOrderRequest updateOrderRequest);

    void deleteOrder(Integer id);

    void deleteOrder2(Integer id);

    void deleteOrder3(Integer id);



}

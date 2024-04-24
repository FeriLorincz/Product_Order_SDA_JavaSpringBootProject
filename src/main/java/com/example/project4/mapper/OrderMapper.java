package com.example.project4.mapper;

import com.example.project4.dto.request.order.AddOrderRequest;
import com.example.project4.dto.response.OrderResponse;
import com.example.project4.entity.Order;
import com.example.project4.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponse fromOrderEntity(Order order){

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setTitle(order.getTitle());
        orderResponse.setStatus(order.getStatus().name());
        List<String> productsName = order.getProducts().stream().map(element ->element.getName()).collect(Collectors.toList());

        orderResponse.setProductsName(productsName);

        return orderResponse;
    }

    public Order fromAddOrderRequest(AddOrderRequest addOrderRequest){

        Order order = new Order();

        order.setTitle(addOrderRequest.getTitle());
        order.setStatus(OrderStatus.PLACED);

        return order;

    }

}

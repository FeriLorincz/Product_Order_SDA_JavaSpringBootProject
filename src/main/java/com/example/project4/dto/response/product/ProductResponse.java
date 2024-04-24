package com.example.project4.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String name;

    private Double price;

    private Integer quantity;

    private String orderTitle; // expunem doar titlul order-ului, nu toate datele sale din obiectul private Order order
}

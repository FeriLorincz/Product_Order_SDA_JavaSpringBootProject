package com.example.project4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id") //entitatea cu Foreign Key (coloana aditionala) e considerata entitatea PARINTE
    private Order order;

    public void updateQuantity(){
        this.quantity = this.quantity-1;
    }

}

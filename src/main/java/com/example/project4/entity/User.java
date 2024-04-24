package com.example.project4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(fetch = FetchType.EAGER) //incarca toate rolurile user-ului din start, nu prin get.role atunci cand e scris in cod si doar acolo      // @JoinTable se pune pe parinte, pe entitatea dominanta
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id")}
            ,inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles=new ArrayList<>();


}

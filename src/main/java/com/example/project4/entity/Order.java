package com.example.project4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "order")  // pt a nu intra in ciclu infinit (stack overflow exception)
@Entity
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Title;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    //@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
            //se pune la nivel de Copil (numele din Product, la final, "private Order order"
            // Fetch = Lazy - e cand vreau sa obtine, citeste, aduce informatii despre ceva, dar nu si despre lista sa de produse
            // Eager = incarca in raspuns automat toate informatiile despre produse pe langa toate celelalte
    //pui cascade pt a sterge sau adauga sa orice altceva cu foreign key automat, daca e @OneTomany sau @OneToOne (cu @Many... nu merge)
    //order e parinte deoarece are in campurile sale si Foreign key-ul (exact acest camp de mai jos)
    private List<Product> products = new ArrayList<>();  //trebuie initializata, altfel vom avea mereu o lista goala


    public void addProducts(List<Product> products){
        this.products.addAll(products);
        products.forEach(product -> product.setOrder(this));
    }
//
//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", Title='" + Title + '\'' +
//                ", created=" + created +
//                ", updated=" + updated +
//                ", status=" + status +
//              //  ", products=" + products +  // asta face adnotarea de mai sus
//                '}';
//    }
}

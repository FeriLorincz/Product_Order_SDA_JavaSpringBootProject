package com.example.project4.service;

import com.example.project4.dto.request.order.AddOrderRequest;
import com.example.project4.dto.request.order.UpdateOrderRequest;
import com.example.project4.dto.response.OrderResponse;
import com.example.project4.entity.Order;
import com.example.project4.entity.Product;
import com.example.project4.exception.order.OrderNotFoundException;
import com.example.project4.mapper.OrderMapper;
import com.example.project4.repository.OrderRepository;
import com.example.project4.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceIMPL implements OrderService {


    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final ProductRepository productRepository;

    public OrderServiceIMPL(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
    }


    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponseList = orders.stream().map(element -> orderMapper.fromOrderEntity(element)).collect(Collectors.toList());
        return orderResponseList;
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            OrderResponse orderResponse = orderMapper.fromOrderEntity(order);
            return orderResponse;
        } else {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
    }

    @Override
    public OrderResponse createOrder(AddOrderRequest addOrderRequest) {


        Order order = orderMapper.fromAddOrderRequest(addOrderRequest);

        List<String> productsName = addOrderRequest.getProductsName();  //informatiile despre produse venite din AddOrderRequest

        List<Product> products = productRepository.findAll().stream()
                .filter(product -> productsName.contains(product.getName())).collect(Collectors.toList());

//        order.setProducts(products);
//        products.forEach(product -> product.setOrder(order));

        order.addProducts(products);  //inlocuieste cele 2 randuri de mai sus cu ultimele (metoda) din Order (din Entity)

        orderRepository.save(order);


        OrderResponse orderResponse = orderMapper.fromOrderEntity(order);

        return orderResponse;

    }

    @Override
    public void updateOrder(Integer id, UpdateOrderRequest updateOrderRequest) {

    }

    @Override
    @Transactional
    public void deleteOrder2(Integer id) {
//        Optional<Order> optionalOrder = orderRepository.findById(id);
//        if(optionalOrder.isPresent())
//        {
//            Order order = optionalOrder.get();
//            List<Product> products = order.getProducts();
//            products.forEach(product ->  productRepository.deleteById(product.getId()));
//        }  // totul e echivalent cu linia de dedesupt ... deleteAll();

        //      productRepository.deleteAll();

        orderRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void deleteOrder(Integer id) { //stergem un order ce are deja un  foreign key - rupem legaturile dupa ce le anulam campurile

        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order orderToBeDeleted = orderOptional.get();
            List<Product> productsToBeDeleted = orderToBeDeleted.getProducts();

            if (!productsToBeDeleted.isEmpty()) {
                productsToBeDeleted.forEach(product -> product.setOrder(null));  // punem nul pe campuri deoarece order e foreign key
                orderToBeDeleted.setProducts(null);    //stergem campul order cu id-ul dorit
            }
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }

    }

    @Override
    @Transactional
    public void deleteOrder3(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            Order orderToBeDeleted = orderOptional.get();
            List<Product> productsToBeUpdated = orderToBeDeleted.getProducts();

            if (!productsToBeUpdated.isEmpty()) {
                productsToBeUpdated.forEach(product -> product.updateQuantity());

                productRepository.saveAll(productsToBeUpdated);// updatam produsele la noua lor cantitate, nu le stergem ca si mai sus

                productsToBeUpdated.forEach(product -> product.setOrder(null));
                orderToBeDeleted.setProducts(null);

            }

            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
    }
}

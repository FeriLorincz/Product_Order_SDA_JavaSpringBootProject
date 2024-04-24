package com.example.project4.controller;


import com.example.project4.dto.request.product.AddProductRequest;
import com.example.project4.dto.request.product.UpdateProductRequest;
import com.example.project4.dto.response.product.ProductResponse;
import com.example.project4.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")   //v1 e prima versiune a API-urilor noastre, folosite in practica
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> responseBody = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductBYOd(@PathVariable Integer id){
        ProductResponse responseBody = productService.getProductById(id);
        return ResponseEntity.status((HttpStatus.OK)).body(responseBody);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProductById(@RequestParam Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid AddProductRequest addProductRequest){

        ProductResponse responseBody = productService.addProduct(addProductRequest);
      //  var responseBody = productService.addProduct(addProductRequest);  // ia orice forma, dar te poti incurca usoer
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Integer id, @RequestBody @Valid UpdateProductRequest updateProductRequest){
        productService.updateProduct(id, updateProductRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

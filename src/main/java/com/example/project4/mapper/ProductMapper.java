package com.example.project4.mapper;

import com.example.project4.dto.request.product.AddProductRequest;
import com.example.project4.dto.request.product.UpdateProductRequest;
import com.example.project4.dto.response.product.ProductResponse;
import com.example.project4.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse fromProductEntity(Product product) {

        ProductResponse response = new ProductResponse();

        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());

        if (product.getOrder() != null) {
            response.setOrderTitle(product.getOrder().getTitle());
        }

        return response;
    }

    public Product fromProductRequest(AddProductRequest addProductRequest){

        Product product = new Product();

        product.setName(addProductRequest.getName());
        product.setQuantity(addProductRequest.getQuantity());
        product.setPrice(addProductRequest.getPrice());

        return product;
    }

    public Product fromProductUpdateRequest(Product productTarget, UpdateProductRequest updateProductRequest){

//        Product product = new Product();
//
//    //    product.setName(updateProductRequest.getName());
//        product.setName(productTarget.getName());
//        product.setQuantity(updateProductRequest.getQuantity());
//        product.setPrice(updateProductRequest.getPrice());

        productTarget.setPrice(updateProductRequest.getPrice());
        productTarget.setQuantity(updateProductRequest.getQuantity());

        return productTarget;
    }
}

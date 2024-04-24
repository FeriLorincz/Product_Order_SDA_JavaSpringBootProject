package com.example.project4.service;

// defineste clar ceea ce vrea sa faca aplicatia noastra, prin contracte descriptive


import com.example.project4.dto.request.product.AddProductRequest;
import com.example.project4.dto.request.product.UpdateProductRequest;
import com.example.project4.dto.response.product.ProductResponse;
import com.example.project4.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Integer id);

    ProductResponse addProduct(AddProductRequest addProductRequest);

    void updateProduct(Integer id, UpdateProductRequest updateProductRequest);

    void deleteProduct(Integer id);

}

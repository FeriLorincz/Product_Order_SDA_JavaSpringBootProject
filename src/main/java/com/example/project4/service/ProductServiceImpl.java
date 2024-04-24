package com.example.project4.service;

import com.example.project4.dto.request.product.AddProductRequest;
import com.example.project4.dto.request.product.UpdateProductRequest;
import com.example.project4.dto.response.product.ProductResponse;
import com.example.project4.entity.Product;
import com.example.project4.exception.product.ProductNotFoundException;
import com.example.project4.mapper.ProductMapper;
import com.example.project4.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;//daca e final, atunci trebuie initializata, deci creat contructor

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    private final ProductMapper productMapper;



    @Override
    public List<ProductResponse> getAllProducts() {

        // cu programare interactiva am face asa:
//        List<Product> products = productRepository.findAll();
//        List<ProductResponse> result = new ArrayList<>();
//        for(Product product : products){
//            ProductResponse productResponse = productMapper.fromProductEntity();
//            result.add(productResponse);
//        }

        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponseList = products.stream().
                map(element ->productMapper.fromProductEntity(element)).collect(Collectors.toList());

        // lamnda  - interfata functionala - are doar 1 singura metoda abstracta - baza programarii functionale (inlocieste programarea interactiva cu for si while_

        return productResponseList;
    }

    @Override
    public ProductResponse getProductById(Integer id) {

       Optional<Product> productOptional = productRepository.findById(id);
       if(productOptional.isPresent()){
           Product product = productOptional.get();
           ProductResponse productResponse = productMapper.fromProductEntity(product);
           return productResponse;
       }
       else{
           throw new ProductNotFoundException("Product with id " +id + " not found");
       }
    }

    @Override
    public ProductResponse addProduct(AddProductRequest addProductRequest) {
        Product product = productMapper.fromProductRequest(addProductRequest);
        productRepository.save(product);

        ProductResponse productResponse = productMapper.fromProductEntity(product);
        return productResponse;
    }

    @Override
    public void updateProduct(Integer id, UpdateProductRequest updateProductRequest) {

        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            Product productToBeUpdated = productMapper.fromProductUpdateRequest(product, updateProductRequest);
            productRepository.save(productToBeUpdated);
        }
        else{
            throw new ProductNotFoundException(" Product with id " +id + " not found");
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}

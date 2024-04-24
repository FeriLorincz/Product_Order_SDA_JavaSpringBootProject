package com.example.project4.dto.request.product;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

//    @NotEmpty(message = "The product name must no be empty")
//    private String name;

    @NotNull(message = "The product price must be defined")
    @Positive(message = "The Product price must be a positive number")
    private Double price;

   // @NotBlank(message = "Out of stock")   nu se pune pe Integer, doar pe String-uri
    @Min(value = 1, message = "Please enter a value greater or equal to 1")
    private Integer quantity;

}

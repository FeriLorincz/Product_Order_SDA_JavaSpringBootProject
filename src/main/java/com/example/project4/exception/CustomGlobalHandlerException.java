package com.example.project4.exception;

import com.example.project4.exception.product.ProductNotFoundException;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomGlobalHandlerException extends ResponseEntityExceptionHandler {


    private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(apiError, apiError.getStatus()); //corpul raspunsului nostru sa fie sub forma unui apiError
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundExecption(ProductNotFoundException exception, HttpServletRequest request){
        ApiError apiError = ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return buildResponseEntity(apiError);
    }




    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        //e o exceptie generala, dar customizata de noi (sau pe care le-am creat noi si le tratam separat, astfel)

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) request).getRequest();

        ApiError apiError = ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message("Message not accepted")
                .path(httpServletRequest.getRequestURI())
                .build();

        return buildResponseEntity(apiError);

    }

}

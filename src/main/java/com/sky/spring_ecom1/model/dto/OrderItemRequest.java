package com.sky.spring_ecom1.model.dto;


public record OrderItemRequest(
        int productId,
        int quantity) {
}

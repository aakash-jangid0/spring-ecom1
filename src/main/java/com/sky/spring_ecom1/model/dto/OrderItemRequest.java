package com.sky.spring_ecom1.model.dto;

import java.math.BigDecimal;

public record OrderItemRequest(
        int productId,
        int quantity,
        BigDecimal price) {
}

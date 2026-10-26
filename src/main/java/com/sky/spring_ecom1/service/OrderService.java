package com.sky.spring_ecom1.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sky.spring_ecom1.model.Order;
import com.sky.spring_ecom1.model.OrderItem;
import com.sky.spring_ecom1.model.Product;
import com.sky.spring_ecom1.model.dto.OrderItemRequest;
import com.sky.spring_ecom1.model.dto.OrderItemResponse;
import com.sky.spring_ecom1.model.dto.OrderRequest;
import com.sky.spring_ecom1.model.dto.OrderResponse;
import com.sky.spring_ecom1.repo.OrderRepo;
import com.sky.spring_ecom1.repo.ProductRepo;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public OrderService(OrderRepo orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        if (request.items() == null || request.items().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An order must contain at least one item");
        }

        Order order = new Order();
        String orderId = "ORD"
            + UUID.randomUUID() .toString()
            .substring(0, 8) .toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(request.customerName());
        order.setEmail(request.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest requestedItem : request.items()) {
            if (requestedItem.quantity() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item quantity must be greater than zero");
            }

            Product product = productRepo.findById(requestedItem.productId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Product not found: " + requestedItem.productId()));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(requestedItem.quantity());
            item.setTotalPrice(BigDecimal.valueOf(product.getPrice())
                    .multiply(BigDecimal.valueOf(requestedItem.quantity())));
            item.setOrder(order);
            orderItems.add(item);
        }
        order.setItems(orderItems);

        return toResponse(orderRepo.save(order));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()))
                .toList();

        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getStatus(),
                order.getOrderDate(),
                items);
    }
}

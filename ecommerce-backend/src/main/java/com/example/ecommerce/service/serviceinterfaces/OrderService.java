package com.example.ecommerce.service.serviceinterfaces;

import com.example.ecommerce.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getCurrentUserOrders(Long user_id);
    OrderResponse getOrderById(Long id);

    Object cancelOrder(Long id);
}

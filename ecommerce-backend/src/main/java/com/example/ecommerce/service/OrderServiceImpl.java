package com.example.ecommerce.service;


import com.example.ecommerce.dto.OrderResponse;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.serviceinterfaces.OrderService;
import com.example.ecommerce.utils.EcommerceConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderResponse> getCurrentUserOrders(Long user_id) {
        try {
            List<Order> activeOrders = orderRepository.findOrdersWithItemsByUserId(user_id);

            List<OrderResponse> orderResponses = new ArrayList<>();

            orderResponses.addAll(
                    activeOrders.stream()
                            .map(order -> new OrderResponse(
                                    order.getId(),
                                    order.getItems(),
                                    order.getTotalAmount(),
                                    order.getCreatedAt(),
                                    order.isCanceled()
                            ))
                            .collect(Collectors.toList()));

            return orderResponses;
        } catch (Exception e) {
            throw new CustomException(EcommerceConstants.USERS_ORDERS_NOT_FOUND);
        }
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new CustomException(EcommerceConstants.ORDER_NOT_FOUND));

            return new OrderResponse(order.getId(), order.getItems(),
                        order.getTotalAmount(), order.getCreatedAt(), order.isCanceled());
        } catch (Exception e) {
            throw new CustomException(EcommerceConstants.ORDER_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public String cancelOrder(Long id) {
        try {
            Order order = orderRepository.findById(id)
                            .orElseThrow(() -> new CustomException(EcommerceConstants.ORDER_NOT_FOUND));

            if (order.isCanceled()) {
                throw new CustomException("Order already canceled");
            }
            for (OrderItem item : order.getItems()) {
                Product product = productRepository.findById(item.getProduct().getId())
                        .orElseThrow(() -> new CustomException(EcommerceConstants.PRODUCTS_NOT_FOUND));

                product.setInventoryCount(product.getInventoryCount() + item.getQuantity());
                productRepository.save(product);
            }

            order.setCanceled(true);
            orderRepository.save(order);
            return "Successful";
        } catch (Exception e) {
            throw new CustomException("Unable to cancel order"+ e.getMessage());
        }
    }
}

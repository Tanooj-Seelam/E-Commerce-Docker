package com.example.ecommerce.dto;

import com.example.ecommerce.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long user_id;
    private List<OrderItem> items;
    private Long total;
    private LocalDateTime timeStamp;
    private boolean isCanceled;
}

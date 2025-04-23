package com.example.ecommerce.service.serviceinterfaces;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.entity.Cart;

public interface CartService {
    Cart getCurrentUserCart(Long user_id);

    String addItemToCart(CartItemRequest cartItemRequest);

    String updateCartItem(CartItemRequest cartItemRequest);

    String removeItemFromCart(Long cartId,Long itemId);

    Object clearCart(Long cart_id);

    Object createOrderFromCart(Long user_id);
}

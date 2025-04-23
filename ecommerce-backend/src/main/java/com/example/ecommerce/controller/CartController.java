package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.service.serviceinterfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping("/all/{user_id}")
    public ResponseEntity<Object> getUserCart(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(cartService.getCurrentUserCart(user_id));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestBody CartItemRequest cartItemRequest) {
        try {
            return ResponseEntity.ok(cartService.addItemToCart(cartItemRequest));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<Object> updateCartItem(@RequestBody CartItemRequest cartItemRequest) {
        try {
            return ResponseEntity.ok(cartService.updateCartItem(cartItemRequest));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<Object> removeFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            return ResponseEntity.ok(cartService.removeItemFromCart(cartId,itemId));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<Object> clearCart(@PathVariable Long cart_id) {
        try {
            return ResponseEntity.ok(cartService.clearCart(cart_id));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout(@PathVariable Long user_id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createOrderFromCart(user_id));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
} 
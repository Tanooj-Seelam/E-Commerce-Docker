package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.dto.OrderResponse;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.serviceinterfaces.CartService;
import com.example.ecommerce.utils.EcommerceConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Cart getCurrentUserCart(Long userId) {
        try {
            return cartRepository.findByUserId(userId)
                    .orElseGet(() -> {
                        Cart cart = new Cart();
                        User user = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomException(EcommerceConstants.USER_NOT_FOUND));
                        cart.setUser(user);
                        cartRepository.save(cart);
                        return cart;
                    });
        } catch (Exception e) {
            throw new CustomException("Failed to retrieve or create cart: " + e.getMessage());
        }
    }

    @Override
    public String addItemToCart(CartItemRequest cartItemRequest) {
        try{
            Cart cart = cartRepository.findByUserId(cartItemRequest.getUser_id())
                    .orElseThrow(() -> new CustomException(EcommerceConstants.USER_CART_NOT_FOUND));

            Product product = productRepository.findById(cartItemRequest.getProduct_id())
                    .orElseThrow(() -> new CustomException(EcommerceConstants.PRODUCTS_NOT_FOUND));

            Optional<CartItem> existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(cartItemRequest.getProduct_id()))
                    .findFirst();

            if (existingItem.isPresent()) {
                CartItem item = existingItem.get();
                item.setQuantity(cartItemRequest.getCount());
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(cartItemRequest.getCount());
                cart.getItems().add(cartItem);
            }

            cartRepository.save(cart);
            return "Item added to cart successfully";

        } catch (Exception e) {
            throw new CustomException("Failed to add item to cart: " + e.getMessage());
        }
    }

    @Override
    public String updateCartItem(CartItemRequest cartItemRequest) {
        try {
            Cart cart = cartRepository.findByUserId(cartItemRequest.getUser_id())
                    .orElseThrow(() -> new CustomException(EcommerceConstants.USER_CART_NOT_FOUND));

            Optional<CartItem> existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(cartItemRequest.getProduct_id()))
                    .findAny();

            if (existingItem.isPresent()) {
                CartItem item = existingItem.get();
                if (item.getQuantity() != cartItemRequest.getCount()) {
                    item.setQuantity(cartItemRequest.getCount());
                }
                cartRepository.save(cart);
                return "Cart has been updated successfully.";
            } else {
                return "Product not found in cart. Nothing to update.";
            }
        } catch (Exception e) {
            throw new CustomException("Failed to update the cart: "+ e.getMessage());
        }
    }

    @Override
    public String removeItemFromCart(Long cart_id, Long itemId) {
        try {
            Cart cart = cartRepository.findById(cart_id)
                    .orElseThrow(() -> new CustomException(EcommerceConstants.USER_CART_NOT_FOUND));

            Optional<CartItem> existingItem = cart.getItems().stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst();

            if(existingItem.isPresent()) {
                cart.getItems().clear();
                cartRepository.save(cart);
                return "Successful";
            }else {
                    return "Nothing to remove";
                }
        } catch (Exception e) {
            throw new CustomException("Failed to remove item from the cart" + e.getMessage());
        }
    }

    @Override
    public Object clearCart(Long cart_id) {
        try {
            Cart cart = cartRepository.findById(cart_id)
                    .orElseThrow(() -> new CustomException(EcommerceConstants.USER_CART_NOT_FOUND));

            if (!cart.getItems().isEmpty()) {
                cart.getItems().removeAll(cart.getItems());
                cartRepository.delete(cart);
                return "Successful";
            } else {
                return "Nothing to remove";
            }
        } catch (Exception e) {
            throw new CustomException("Unable to remove the cart" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public OrderResponse createOrderFromCart(Long user_id) {
        try {
            Cart cart = cartRepository.findByUserId(user_id)
                    .orElseThrow(() -> new CustomException(EcommerceConstants.CHECKOUT_ERROR));

            List<CartItem> cartItems = cart.getItems();
            if (cartItems.isEmpty()) {
                throw new CustomException("Cart is empty");
            }

            Order newOrder = new Order();
            newOrder.setUser(cart.getUser());
            List<OrderItem> orderItems = new ArrayList<>();
            Long total = 0L;

            for (CartItem cartItem : cartItems) {
                Product product = cartItem.getProduct();
                if (product.getInventoryCount() < cartItem.getQuantity()) {
                    throw new CustomException("Insufficient stock for product: " + product.getName());
                }

                int updatedInventory = product.getInventoryCount() - cartItem.getQuantity();
                product.setInventoryCount(updatedInventory);
                productRepository.save(product);

                if (product != null) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(newOrder);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(cartItem.getQuantity());
                    Long price = cartItem.getQuantity() * product.getPrice();
                    orderItem.setPriceAtPurchase(price);
                    orderItems.add(orderItem);
                    total += price;
                }
            }

            newOrder.setItems(orderItems);
            newOrder.setTotalAmount(total);
            newOrder.setCreatedAt(LocalDateTime.now());
            orderRepository.save(newOrder);

            cart.getItems().clear();
            cartRepository.save(cart);

            return new OrderResponse(
                    newOrder.getId(),
                    newOrder.getItems(),
                    newOrder.getTotalAmount(),
                    newOrder.getCreatedAt(),
                    false);
        } catch (Exception e) {
            throw new CustomException(EcommerceConstants.CHECKOUT_ERROR);
        }
    }
}

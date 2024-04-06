package com.cncservice.ecommerce.controller;

import com.cncservice.ecommerce.entity.CartItem;
import com.cncservice.ecommerce.model.CartItemModel;
import com.cncservice.ecommerce.model.CheckoutModel;
import com.cncservice.ecommerce.service.CartItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {
  private final CartItemService cartItemService;

  public CartItemController(CartItemService cartItemService) {
    this.cartItemService = cartItemService;
  }

  @PostMapping
  public CartItemModel saveCartItem(@RequestBody CartItem cartItem) {
    return this.cartItemService.saveCartItem(cartItem);
  }
    @PutMapping
    public CartItemModel updateCartItem(@RequestBody CartItemModel cartItem) {
      return this.cartItemService.updateCartItem(cartItem);
    }

    @GetMapping("/user/{id}")
    public CheckoutModel getCartItemsByUserId(String id) {
      return this.cartItemService.getCheckout(id);
    }
}

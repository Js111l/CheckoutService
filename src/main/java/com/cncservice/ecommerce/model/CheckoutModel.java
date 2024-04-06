package com.cncservice.ecommerce.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CheckoutModel {
    private List<CartItemModel> cartItems;
    private BigDecimal finalPrice;
}

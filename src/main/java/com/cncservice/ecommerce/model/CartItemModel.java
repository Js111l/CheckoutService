package com.cncservice.ecommerce.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemModel {
    private String id;
    private long productId;
    private String userId;
    private long quantity;
    private BigDecimal price;
}

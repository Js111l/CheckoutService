package com.cncservice.ecommerce.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPriceModel {
    private Long productId;
    private BigDecimal priceForOnePiece;
}
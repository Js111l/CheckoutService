package com.cncservice.ecommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class CartItemSequenceDocument {
  @Id private String id = "cart_items_sequence";
  private long sequenceValue;
}

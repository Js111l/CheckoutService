package com.cncservice.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart_items")
@AllArgsConstructor
@Data
@Setter
@Getter
public class CartItem {
  @Id private String id;
  private Long productId;
  private String userId;
  private long quantity;
}

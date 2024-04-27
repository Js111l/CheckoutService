package com.cncservice.ecommerce.entity;

import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "cart_items")
@AllArgsConstructor
@Data
@Setter
@Getter
public class CartItem extends BaseEntity {
  private Long productId;
  private String userId;
  private long quantity;
}

package com.cncservice.ecommerce.repository;

import com.cncservice.ecommerce.entity.CartItem;
import com.cncservice.ecommerce.model.CartItemModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
}

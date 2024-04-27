package com.cncservice.ecommerce.repository;

import com.cncservice.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select ci from CartItem ci where ci.userId in :userId")
    List<CartItem> findAllByUserId(@Param("userId") List<String> userId);
}

package com.cncservice.ecommerce.service;

import com.cncservice.ecommerce.client.PriceClient;
import com.cncservice.ecommerce.entity.CartItem;
import com.cncservice.ecommerce.exception.ErrorKey;
import com.cncservice.ecommerce.exception.LogicalException;
import com.cncservice.ecommerce.mapper.CartItemMapper;
import com.cncservice.ecommerce.mapper.CheckoutMapper;
import com.cncservice.ecommerce.model.CartItemModel;
import com.cncservice.ecommerce.model.CheckoutModel;
import com.cncservice.ecommerce.repository.CartItemRepository;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@SafeTransactional
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final PriceClient priceClient;

    public CartItemModel saveCartItem(final CartItem cartItem) {
        var model = CartItemMapper.INSTANCE.entityToModel(this.cartItemRepository.save(cartItem));
        model.setPrice(this.priceClient.calculateFinalPrice(List.of(cartItem.getProductId())).get(0).getPriceForOnePiece());
        return model;
    }

    public CheckoutModel getCheckout(String userId) {
        var checkout = CheckoutMapper.INSTANCE.entityToModel(this.cartItemRepository.findAllByUserId(List.of(userId)));
        final var productIds = checkout.getCartItems().stream().map(CartItemModel::getProductId).toList();
        //TODO  do poprawy!!
        var productPriceModels = this.priceClient.calculateFinalPrice(productIds);
        checkout.getCartItems().forEach(cartItemModel -> {
            var model = productPriceModels.stream().filter(x -> x.getProductId().equals(cartItemModel.getProductId())).findFirst().orElseThrow();
            cartItemModel.setPrice(model.getPriceForOnePiece());
        });
        checkout.setFinalPrice(calculateFinalPrice(checkout.getCartItems()));
        return checkout;
    }

    public CartItemModel updateCartItem(final CartItemModel cartItem) {
        var from = CartItemMapper.INSTANCE.modelToEntity(cartItem);
        var to =
                this.cartItemRepository.findById(from.getId()).orElseThrow(() -> new LogicalException(ErrorKey.NOT_FOUND));
        to.setQuantity(from.getQuantity());
        to.setProductId(from.getProductId());
        to.setUserId(from.getUserId());
        var model = CartItemMapper.INSTANCE.entityToModel(this.cartItemRepository.save(to));
        model.setPrice(this.priceClient.calculateFinalPrice(List.of(cartItem.getProductId())).get(0).getPriceForOnePiece());
        return model;
    }

    public BigDecimal calculateFinalPrice(List<CartItemModel> cartItemModels) {
        return cartItemModels.stream().map(CartItemModel::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

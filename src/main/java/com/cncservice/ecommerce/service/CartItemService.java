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
import com.cncservice.ecommerce.sequence.CartItemSequenceGenerator;
import com.cncservice.ecommerce.entity.CartItemSequenceDocument;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@SafeTransactional
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final MongoTemplate mongoTemplate;
    private final PriceClient priceClient;

    public CartItemModel saveCartItem(final CartItem cartItem) {
        final var id =
                new CartItemSequenceGenerator()
                        .retrieveIncrementedId(
                                "cart_items_sequence", CartItemSequenceDocument.class, this.mongoTemplate);
        cartItem.setId(String.valueOf(id));
        var model = CartItemMapper.INSTANCE.entityToModel(this.cartItemRepository.save(cartItem));
        // TODO: 06.04.2024  
        model.setPrice(this.priceClient.calculateFinalPrice(List.of(cartItem.getProductId())).get(0).getPriceForOnePiece());
        return model;
    }

    public CheckoutModel getCheckout(String userId) {
        var checkout = CheckoutMapper.INSTANCE.entityToModel(this.cartItemRepository.findAllById(List.of(userId)));
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
        var model= CartItemMapper.INSTANCE.entityToModel(this.cartItemRepository.save(to));
        // TODO: 06.04.2024
        model.setPrice(this.priceClient.calculateFinalPrice(List.of(cartItem.getProductId())).get(0).getPriceForOnePiece());
        return model;
    }

    public BigDecimal calculateFinalPrice(List<CartItemModel> cartItemModels) {
        return cartItemModels.stream().map(CartItemModel::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

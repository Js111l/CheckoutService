package com.cncservice.ecommerce.mapper;

import com.cncservice.ecommerce.entity.CartItem;
import com.cncservice.ecommerce.model.CartItemModel;
import com.cncservice.ecommerce.model.CheckoutModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemModel entityToModel(CartItem entity);

    CartItem modelToEntity(CartItemModel model);

}

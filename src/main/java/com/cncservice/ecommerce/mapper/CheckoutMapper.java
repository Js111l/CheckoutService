package com.cncservice.ecommerce.mapper;

import com.cncservice.ecommerce.entity.CartItem;
import com.cncservice.ecommerce.model.CheckoutModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface CheckoutMapper {
    CheckoutMapper INSTANCE = Mappers.getMapper(CheckoutMapper.class);
    CheckoutModel entityToModel(List<CartItem> itemList);
}

package com.cncservice.ecommerce.client;

import com.cncservice.ecommerce.model.ProductPriceModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "priceServiceClient",url = "${price.service.url}")
public interface PriceClient {
    @PostMapping("/prices")
    List<ProductPriceModel> calculateFinalPrice(@RequestBody List<Long> productIds);
}

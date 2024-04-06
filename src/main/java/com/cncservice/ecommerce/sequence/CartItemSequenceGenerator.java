package com.cncservice.ecommerce.sequence;

import com.cncservice.ecommerce.entity.CartItemSequenceDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class CartItemSequenceGenerator implements SequenceGenerator {

  @Override
  public long retrieveIncrementedId(String id, Class<?> cls, MongoTemplate mongoTemplate) {
    var sequenceDocument = (CartItemSequenceDocument) mongoTemplate.findById(id, cls);
    if (sequenceDocument == null) {
      var newSequenceDocument = new CartItemSequenceDocument();
      newSequenceDocument.setSequenceValue(1);
      mongoTemplate.save(newSequenceDocument);
      return newSequenceDocument.getSequenceValue();
    }
    sequenceDocument.setSequenceValue(sequenceDocument.getSequenceValue() + 1);
    mongoTemplate.save(sequenceDocument);
    return sequenceDocument.getSequenceValue();
  }
}

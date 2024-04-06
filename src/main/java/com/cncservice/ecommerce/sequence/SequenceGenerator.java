package com.cncservice.ecommerce.sequence;

import org.springframework.data.mongodb.core.MongoTemplate;

public interface SequenceGenerator {
  long retrieveIncrementedId(String id, Class<?> cls, MongoTemplate mongoTemplate);
}

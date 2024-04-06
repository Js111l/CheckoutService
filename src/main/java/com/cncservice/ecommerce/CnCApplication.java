package com.cncservice.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.cncservice.ecommerce.repository")
public class CnCApplication {
  public static void main(String[] args) {
    SpringApplication.run(CnCApplication.class, args);
  }
}

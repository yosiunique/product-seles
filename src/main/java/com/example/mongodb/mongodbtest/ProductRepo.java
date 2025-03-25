package com.example.mongodb.mongodbtest;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo  extends MongoRepository<Product ,String>{
    
}

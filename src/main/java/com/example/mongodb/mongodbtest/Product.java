package com.example.mongodb.mongodbtest;


import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Data
@Document
public class Product {
    @Id
   
    private String id=UUID.randomUUID().toString();
    private String productName;
    private String price ;
    private String category;
    
}

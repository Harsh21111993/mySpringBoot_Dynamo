package com.delta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    // @Value("${aws.dynamodb.endpoint}")
    // private String dynamodbEndpoint;

    // @Value("${aws.region}")
    // private String awsRegion;

    // @Value("${aws.dynamodb.accessKey}")
    // private String dynamodbAccessKey;

    // @Value("${aws.dynamodb.secretKey}")
    // private String dynamodbSecretKey;


    @Bean
    public DynamoDbClient dynamoDbClient(){
return DynamoDbClient.builder().
build();
    }
    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(){
return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient()).build();
    }
    @Bean
    public DynamoDbTable<Resource> resourceDetailsTable(){
        return dynamoDbEnhancedClient().table(com.delta.entity.Resource.tableName(), TableSchema.fromBean(Resource.class));
    }
    
}

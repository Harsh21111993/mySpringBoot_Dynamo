package com.delta.config;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.delta.entity.Resource;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.internal.waiters.ResponseOrException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;
@Slf4j
public class CreateTable  implements ApplicationListener<ApplicationReadyEvent>{
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        
        DynamoDbTable<Resource> resourceDbTable= event.getApplicationContext().getBean(DynamoDbTable.class);
        
        DynamoDbClient dynamoDbClient = event.getApplicationContext().getBean(DynamoDbClient.class);

        if(!tableExists(dynamoDbClient ,Resource.tableName())){
     
            log.info("Creating table ..." ,Resource.tableName());

            resourceDbTable.createTable();
        
        
        try(DynamoDbWaiter waiter = DynamoDbWaiter.builder().client(dynamoDbClient).build()){

            ResponseOrException<DescribeTableResponse> response =waiter
                  .waitUntilTableExists(build -> build.tableName(Resource.tableName()).build()).matched();
              DescribeTableResponse tableDescription =response.response().orElseThrow(()-> 
              new RuntimeException(Resource.tableName() + " Table was not created "));

              log.info(Resource.tableName(), " Table is created");

            }
    } else{
        log.info("Table is already exists " , Resource.tableName());
    }
    
    }

private boolean tableExists(DynamoDbClient dynamoDbClient,String tableName){

    DescribeTableRequest request =  DescribeTableRequest.builder().tableName(tableName).build();

    try{
        TableDescription tableInfo = dynamoDbClient.describeTable(request).table();
        if(tableInfo != null){
            return true;
        }
        
    } catch (Exception e) {
        log.info("Error while fetching table details ", e.getMessage());
    }
    return false;
}
}

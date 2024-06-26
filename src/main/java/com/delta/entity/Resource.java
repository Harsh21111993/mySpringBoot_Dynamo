package com.delta.entity;

import lombok.Data;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
@Setter
public class Resource {
    
    
    private String mId;
    private String name;
    private String competency;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getmId() {
        return mId;
    }


    @DynamoDbAttribute("name")
    public String getname() {
        return name;
    }
    @DynamoDbAttribute("competency")
    public String getcompetency() {
        return competency;
    }

   public static String tableName(){
    return "ResourceDetailsTable";
   }

    public void setmId(String mId) {
        this.mId = mId;
    }

}

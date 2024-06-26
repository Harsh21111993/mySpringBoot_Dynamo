package com.delta.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delta.entity.Resource;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
@AllArgsConstructor
public class ResourceDetailRepository {
     @Autowired
    private DynamoDbTable<Resource> resourceTable;

    public Resource create(Resource resource){
    resourceTable.putItem(resource);;
    return resource;
    }
    public Resource get(Resource resource){
        return resourceTable.getItem(resource);
    }
    public void update(Resource resource){
   resourceTable.updateItem(resource);
    }
    public Resource delete(String mid){
  Key deleteKey = Key.builder().partitionValue(mid).build();
  return resourceTable.deleteItem(deleteKey);
    }


}

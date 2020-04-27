package com.espressif.cache.webservice.service;

import com.espressif.cache.webservice.domain.data.DoublyLinkedList;
import com.espressif.cache.webservice.domain.data.DoublyLinkedListNode;
import com.espressif.cache.webservice.model.LRUResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LRUCacheService {
    private static final Logger logger = LoggerFactory.getLogger(LRUCacheService.class);

    private int maxSize;
    private Map<String, DoublyLinkedListNode> cache;
    private int currentSize;
    private DoublyLinkedList listOfMostRecent;

    public LRUCacheService(@Value("${cache.lru.maxsize}") int maxSize){
        if(maxSize <=0){
            this.maxSize = 10;  //Default size of the cache
        } else {
            this.maxSize = maxSize;
        }

        this.currentSize = 0;
        this.cache = new HashMap<>();
        this.listOfMostRecent = new DoublyLinkedList();
    }

    public void insertKeyValuePair(String key, int value){
        logger.info("adding new key-value pair into the cache -> key: {}, value: {}",key,value);
        if (!cache.containsKey(key)){
            if (currentSize == maxSize){
                evictLeastRecent();
            }else {
                currentSize++;
            }
            cache.put(key, new DoublyLinkedListNode(key, value));
        }else {
            replaceKey(key, value);
        }
        updateMostRecent(cache.get(key));
        logger.info("successfully added new key-value pair into the cache -> key: {}, value: {}",key,value);
    }

    public LRUResult getValueFromKey(String key){
        logger.info("retrieving a key-value pair from the cache for key: {}",key);
        if(!cache.containsKey(key)){
            logger.info("key: {} doesn't exist in the current cache",key);
            return new LRUResult(false, -1);
        }
        updateMostRecent(cache.get(key));
        return new LRUResult(true, cache.get(key).getValue());
    }

    private void evictLeastRecent(){
        logger.info("removing the least recently used key-value pair from the cache");
        String keyToRemove = listOfMostRecent.getTail().getKey();
        listOfMostRecent.removeTail();
        cache.remove(keyToRemove);
        logger.info("successfully removed the least recently used key: {} from the cache",keyToRemove);
    }

    private void updateMostRecent(DoublyLinkedListNode node){
        listOfMostRecent.setHeadTo(node);
    }

    private void replaceKey(String key, int value){
        if(!this.cache.containsKey(key)){
            return;
        }
        cache.get(key).setValue(value);
    }
}

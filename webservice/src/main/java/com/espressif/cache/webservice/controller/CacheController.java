package com.espressif.cache.webservice.controller;


import com.espressif.cache.webservice.model.LRUResult;
import com.espressif.cache.webservice.model.v1.request.CacheAddRequest;
import com.espressif.cache.webservice.model.v1.response.CacheAddResponse;
import com.espressif.cache.webservice.model.v1.response.CacheGetResponse;
import com.espressif.cache.webservice.service.LRUCacheService;
import com.espressif.cache.webservice.utils.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cache")
public class CacheController {
    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private LRUCacheService lruCacheService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(@RequestBody CacheAddRequest addRequest){
        logger.info("Add key request received: {}", addRequest);
        lruCacheService.insertKeyValuePair(addRequest.getKey(), addRequest.getValue());
        CacheAddResponse response = new CacheAddResponse(ResponseMessage.ADD_KEY_SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{key}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> get(@PathVariable String key){
        logger.info("key retrieve request received for key: {}", key);
        LRUResult result = lruCacheService.getValueFromKey(key);
        if(result.isFound()){
            CacheGetResponse response = new CacheGetResponse(key, result.getValue());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        logger.info("key: {} not found in the current cache",key);
        return ResponseEntity.notFound().build();
    }

}

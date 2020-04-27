# CacheService
REST APIs for caching service using LRU algorithm.

In this project we have implemented two RESTful end points for Inserting the key and getting the key from the Cache:

1. POST Request end-point:  http://localhost:8080/api/v1/cache
2. GET  Request end-point:  http://localhost:8080/api/v1/cache/{key} 

# Configurations

To configure the max size of the cache, ou can use application.properties file as :

    cache.lru.maxsize=10
    
# Enviornment Setup

Java Version: Java 8 and above
Build Tool: gradle

# Swagger Documentation

This project is enabled with swagger documentation where you can try out the APIs.
To open swagger play ground, enter following URL in your browser:
  
  http://localhost:8080/swagger-ui.html

# Docker Integration

Added Google's JIB plugins to dockerize this project without Dockerfile.
This plugin can also be used to push it to public/private cloud registry. 





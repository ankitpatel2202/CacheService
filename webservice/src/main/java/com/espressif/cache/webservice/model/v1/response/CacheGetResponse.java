package com.espressif.cache.webservice.model.v1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheGetResponse {
    private String key;
    private int value;
}

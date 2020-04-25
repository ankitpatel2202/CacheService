package com.espressif.cache.webservice.model.v1.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheAddRequest {
    @NonNull
    private String key;

    @NonNull
    private int value;
}


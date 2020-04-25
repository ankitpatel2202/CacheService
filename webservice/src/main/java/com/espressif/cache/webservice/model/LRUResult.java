package com.espressif.cache.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LRUResult {
    private boolean found;
    private int value;
}

package org.example.asterixapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdService {

    private final String config;

    public IdService(@Value("${custom.config}") String config) {
        this.config = config;
    }

    public String randomId() {
        System.out.println(config);
        return UUID.randomUUID().toString();
    }
}

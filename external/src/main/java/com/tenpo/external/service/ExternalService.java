package com.tenpo.external.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExternalService {

    public float calculatePercentage() {
        return new Random().nextInt(90) + 10;
    }

}

package com.example.springtheory.temp;

public class GreetingServiceGood {
    private static final GreetingServiceGood instance = new GreetingServiceGood();

    private GreetingServiceGood() {
    }

    static GreetingServiceGood getInstance() {
        return instance;
    }

    String greet(String reqName) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return reqName;
    }
}

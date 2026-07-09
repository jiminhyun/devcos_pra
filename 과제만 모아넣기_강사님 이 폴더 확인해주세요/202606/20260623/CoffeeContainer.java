package com.example.springtheory.temp;

public class CoffeeContainer {

    CoffeeMaker getCoffeeMaker() {
        Bean bean = new ColombiaBean();
        return new CoffeeMaker(bean);
    }
}

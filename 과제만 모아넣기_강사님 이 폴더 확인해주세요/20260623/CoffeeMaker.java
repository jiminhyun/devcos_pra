package com.example.springtheory.temp;

public class CoffeeMaker {
    //Bean bean = new ColombiaBean();
    Bean bean;

    public CoffeeMaker(Bean bean) {
        this.bean = bean;
    }

    void brew() {
        System.out.println(bean.name()+"로 커피를 내립니다");
    }
}

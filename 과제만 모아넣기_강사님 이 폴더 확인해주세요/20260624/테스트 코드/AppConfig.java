package com.example.junit_5.temp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean //무조건 객체 반환 해야하는 메서드
    public ProductDao productDao() {
        return new ProductDao();
    }
}

package com.example.spring.springtheory.ch05.ex_5_4;



import com.example.spring.springtheory.ch05.ex_5_2.dao.DaoFactory;
import com.example.spring.springtheory.ch05.ex_5_2.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Start {
    static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        UserService userService = context.getBean("userService", UserService.class);

        userService.upgradeLevels();
    }
}
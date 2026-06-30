package com.example.spring.springtheory.ch05.ex_5_4.service;

import com.example.spring.springtheory.ch05.ex_5_4.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

public class UserServiceTx implements UserService {

    private PlatformTransactionManager transactionManager;
    private UserServiceImpl userService;

    public UserServiceTx(PlatformTransactionManager transactionManager, UserServiceImpl userService) {
        this.transactionManager = transactionManager;
        this.userService = userService;
    }

    @Override
    public void add(User user) throws SQLException, ClassNotFoundException {
        userService.add(user);
    }

    @Override
    public void upgradeLevels() {
        // 1) 트랜잭션 시작 (어떤 기술인지 모른 채, 추상화된 매니저에게 맡긴다)
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            userService.upgradeLevels();
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new RuntimeException("레벨 업그레이드 오류 롤백 발생!",e);
        }
    }
}

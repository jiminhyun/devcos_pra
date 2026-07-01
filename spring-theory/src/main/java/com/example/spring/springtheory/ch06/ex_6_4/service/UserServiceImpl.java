package com.example.spring.springtheory.ch06.ex_6_4.service;

import com.example.spring.springtheory.ch06.ex_6_4.dao.Level;
import com.example.spring.springtheory.ch06.ex_6_4.dao.UserDAO;
import com.example.spring.springtheory.ch06.ex_6_4.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserServiceImpl(com.example.spring.springtheory.ch06.ex_6_1.dao.UserDAO userDAO) {
    }

    //신규가입
    @Override
    public void add(User user) throws SQLException, ClassNotFoundException {
        user.setLevel(Level.BASIC);
        userDAO.add(user);
    }

    // 업그레이드 담당
    @Transactional
    @Override
    public void upgradeLevels() {
        try {
            List<User> users = userDAO.getAll();
            for(User user: users) {
                if(canUpgrade(user)) {
                    upgradeLevel(user);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("레벨 업그레이드 오류 롤백 발생!",e);
        }

        //[짚고 넘어갈 점 -> 다음 단계 추상화]
        //  이 반복 도중 중간에서 예외가 나면, 앞쪽 사용자는 이미 update 되고 뒤쪽은 안 된 채로 끝난다.
//  '전부 성공 아니면 전부 취소(원자성)'가 보장되지 않는 것이다.
//  -> 이를 해결하는 것이 '트랜잭션'이고, 스프링이 이를 기술과 무관하게 다루도록 해주는 것이
//     바로 5장의 핵심인 '트랜잭션 서비스 추상화'다. (이어지는 예제에서 다룬다)
    }

    private boolean canUpgrade(User user) {
        Level curLevel = user.getLevel();
        switch (curLevel) {
            case BASIC:
                return user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER;
            case SILVER:
                return user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD;
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unexpected value" + curLevel);
        }
    }

    //실제 업그레이드
    protected void upgradeLevel(User user) throws SQLException, ClassNotFoundException {
        user.upgradeLevel();
        userDAO.update(user);
    }
}

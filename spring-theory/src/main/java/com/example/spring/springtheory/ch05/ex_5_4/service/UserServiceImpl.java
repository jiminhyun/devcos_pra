package com.example.spring.springtheory.ch05.ex_5_4.service;

import com.example.spring.springtheory.ch05.ex_5_4.dao.Level;
import com.example.spring.springtheory.ch05.ex_5_4.dao.UserDAO;
import com.example.spring.springtheory.ch05.ex_5_4.domain.User;

import java.sql.SQLException;
import java.util.List;

// [트랜잭션 추상화로 가는 3단계]
//  1단계) 직접 JDBC 트랜잭션:
//     UserService가 Connection을 만들어 setAutoCommit(false) -> commit/rollback 하고,
//     그 Connection을 DAO 메서드마다 파라미터로 넘긴다.
//     문제: (a) 서비스가 JDBC API(Connection)에 종속된다.
//           (b) DAO 메서드 시그니처가 Connection으로 더럽혀진다(계층 침범).
//           (c) JDBC 전용이라 JPA/JTA로 바꾸면 코드를 다 고쳐야 한다.
//
//  2단계) 트랜잭션 동기화:
//     Connection을 파라미터로 넘기지 않고 '동기화 저장소'에 묶어두면,
//     DAO는 DataSourceUtils.getConnection()으로 같은 커넥션을 알아서 가져온다(JdbcContext가 이미 적용).
//     -> DAO 시그니처는 깨끗해졌지만, 여전히 'JDBC(DataSource)' 전용이다.
//
//  3단계) 트랜잭션 추상화 (이 코드):
//     스프링이 제공하는 PlatformTransactionManager 인터페이스에만 의존한다.
//     -> 실제 구현(DataSourceTransactionManager=JDBC, JpaTransactionManager=JPA,
//        JtaTransactionManager=분산 트랜잭션)은 설정에서 갈아 끼우면 된다.
//     -> UserService 코드는 기술이 바뀌어도 그대로다. 이것이 '서비스 추상화'다.
public class UserServiceImpl implements UserService {

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private UserDAO userDAO;
    private MailSender mailSender;

    public UserServiceImpl(UserDAO userDAO, MailSender mailSender) {
        this.userDAO = userDAO;
        this.mailSender = mailSender;
    }

    //신규가입
    @Override
    public void add(User user) throws SQLException, ClassNotFoundException {
        user.setLevel(Level.BASIC);
        userDAO.add(user);
    }

    // 업그레이드 담당
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

    // '메일을 만들어 보낸다'까지만 한다. '어떻게 실제로 보내는가'는 주입된 mailSender에 맡긴다.
    private void sendUpgradeEmail(User user) {
        // User에 email 필드가 없으므로 예시로 id를 주소처럼 사용한다(실무라면 user.getEmail()).
        Mail mail = new Mail(
                user.getId(),
                "[안내] 등급이 업그레이드되었습니다",
                user.getName() + "님의 등급이 " + user.getLevel() + " 로 변경되었습니다."
        );
        mailSender.send(mail);
    }
}

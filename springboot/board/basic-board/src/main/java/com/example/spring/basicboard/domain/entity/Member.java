package com.example.spring.basicboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

//회원 엔티티 - member 테이블과 매핑된다.
@Entity // @Entity 어노테이션은 JPA를 사용해 테이블과 매핑할 클래스에 붙여주는 어노테이션이다. 이 어노테이션을 붙임으로써 JPA가 해당 클래스를 관리하게 된다.
@Table(name = "member") // @Table은 엔티티와 매핑할 테이블을 지정

//속성	기능
//        name	매핑할 테이블 이름
//        생략시 엔티티 이름(@Entity(name="~") 사용
//                           catalog	catalog 기능이 있는 DB에서 catalog 매핑
//                           schema	schema기능이 있는 DB에서 schema 매핑
//                           uniqueContraints	DDL 생성시 유니크 제약조건 생성
//                           ※ 스키마 자동 생성 기능을 사용해 DDL을 만들 때만 사용
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED) // JPA는 기본 생성자가 필요하지만 외부에서 무분별한 생성을 막는다.
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment
    private Long id;

    @Column(nullable = false, length = 50)
    private String userId;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String userName;
}

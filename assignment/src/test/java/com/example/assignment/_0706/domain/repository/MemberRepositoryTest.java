package com.example.assignment._0706.domain.repository;

import com.example.assignment._0706.domain.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(Member.builder()
                .userId("test")
                .password("1234")
                .userName("hong")
                .build());
    }

    @Test
    void existsByUserId_true_false() {
        assertThat(memberRepository.existsByUserId("test")).isTrue();
        assertThat(memberRepository.existsByUserId("none")).isFalse();
    }

    @Test
    void findByUserId_있음_없음() {
        assertThat(memberRepository.findByUserId("test")).isPresent();
        assertThat(memberRepository.findByUserId("none")).isEmpty();
    }
}
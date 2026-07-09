package com.example.assignment._0706.service;

import com.example.assignment._0706.domain.entity.Member;
import com.example.assignment._0706.domain.repository.MemberRepository;
import com.example.assignment._0706.dto.LoginRequestDto;
import com.example.assignment._0706.dto.MemberJoinRequestDto;
import com.example.assignment._0706.exception.DuplicateUserIdException;
import com.example.assignment._0706.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberMapper memberMapper;
    @InjectMocks
    MemberService memberService;

    Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .userId("test")
                .password("1234")
                .userName("hong")
                .build();
    }

    @Test
    void login_성공() {
        given(memberRepository.findByUserId("test")).willReturn(Optional.of(member));

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("1234");

        Optional<Member> result = memberService.login(requestDto);
        assertThat(result).isPresent();
    }

    @Test
    void login_비밀번호_틀림_빈_Optional() {
        given(memberRepository.findByUserId("test")).willReturn(Optional.of(member));

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("9999");

        Optional<Member> result = memberService.login(requestDto);
        assertThat(result).isEmpty();
    }

    @Test
    void login_없는_아이디_빈_Optional() {
        given(memberRepository.findByUserId("none")).willReturn(Optional.empty());

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("none");
        requestDto.setPassword("1234");

        Optional<Member> result = memberService.login(requestDto);
        assertThat(result).isEmpty();
    }

    @Test
    void join_성공() {
        MemberJoinRequestDto dto = new MemberJoinRequestDto();
        dto.setUserId("test");
        dto.setPassword("1234");
        dto.setUserName("hong");

        given(memberRepository.existsByUserId("test")).willReturn(false);
        given(memberMapper.toEntity(dto)).willReturn(member);
        memberService.join(dto);



        verify(memberRepository).save(member);
    }

    @Test
    void join_중복() {
        MemberJoinRequestDto dto = new MemberJoinRequestDto();
        dto.setUserId("test");
        dto.setPassword("1234");
        dto.setUserName("hong");
        given(memberRepository.existsByUserId("test")).willReturn(true);

        assertThatThrownBy(()->memberService.join(dto)).isInstanceOf(DuplicateUserIdException.class);
        verify(memberRepository, never()).save(any());
    }
}
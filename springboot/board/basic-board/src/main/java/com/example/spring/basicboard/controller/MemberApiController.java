package com.example.spring.basicboard.controller;

import com.example.spring.basicboard.dto.LoginRequestDto;
import com.example.spring.basicboard.dto.LoginResponseDto;
import com.example.spring.basicboard.dto.MemberJoinRequestDto;
import com.example.spring.basicboard.dto.MemberJoinResponseDto;
import com.example.spring.basicboard.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public MemberJoinResponseDto join(@RequestBody MemberJoinRequestDto dto) {
        memberService.join(dto);
        return new MemberJoinResponseDto("/members/login"); //js에서 해당 url로 리다이렉트
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto,
                                  HttpSession session) {
        return memberService.login(dto)
                .map(
                member -> {
                    session.setAttribute("userId", member.getUserId());
                    session.setAttribute("userName", member.getUserName());
                    return LoginResponseDto.success();
                }
        ).orElseGet(LoginResponseDto::fail); //로그인 false시 호출(전에 흐름을 보면 파악 가능)
    }
}

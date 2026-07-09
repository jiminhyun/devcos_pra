package com.example.assignment._0706.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponseDto {
    private boolean successed;
    private String url;
    private String message;

    public static LoginResponseDto success() {
        return new LoginResponseDto(true, "/", "로그인 성공!");
    }

    public static LoginResponseDto fail() {
        return new LoginResponseDto(false, null, "아이디나 비밀번호가 일치하지 않습니다.");
    }
}

package com.example.assignment._0706.controller;

import com.example.assignment._0706.exception.DuplicateUserIdException;
import com.example.assignment._0706.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    MemberService memberService;

    @Test
    void join_성공() throws Exception {
        //given
        String requestJson = objectMapper.writeValueAsString(//jackson 라이브러리 ObjectMapper를 이용해서 write..라는 java객체 -> json 변환을 담당하는 메서드
                Map.of("userId", "test",
                        "password", "1234",
                        "userName", "test")
        );

        //when, then

        mockMvc.perform(
                post("/api/members/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("/members/login")); //return 타입의 dto필드 부분 검증

    }

    @Test
    void join_중복() throws Exception {
        //given
        willThrow(new DuplicateUserIdException("회원가입 아이디가 중복됩니다."))
                .given(memberService).join(any());

        String requestJson = objectMapper.writeValueAsString(//jackson 라이브러리 ObjectMapper를 이용해서 write..라는 java객체 -> json 변환을 담당하는 메서드
                Map.of("userId", "test",
                        "password", "1234",
                        "userName", "test")
        );

        //when, then

        mockMvc.perform(
                        post("/api/members/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                ).andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("회원가입 아이디가 중복됩니다.")); //throw된 예외의 message필드 검증

    }

}
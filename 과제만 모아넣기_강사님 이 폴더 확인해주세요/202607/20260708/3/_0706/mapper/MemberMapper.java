package com.example.assignment._0706.mapper;

import com.example.assignment._0706.domain.entity.Member;
import com.example.assignment._0706.dto.MemberJoinRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(MemberJoinRequestDto dto) {
        return Member.builder().userId(dto.getUserId()).password(dto.getPassword()).userName(dto.getUserName()).build();
    }
}

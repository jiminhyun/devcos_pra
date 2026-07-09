package com.example.assignment._0706.service;

import com.example.assignment._0706.domain.repository.MemberRepository;
import com.example.assignment._0706.dto.MemberJoinRequestDto;
import com.example.assignment._0706.exception.DuplicateUserIdException;
import com.example.assignment._0706.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public void join(MemberJoinRequestDto dto) {
        if(memberRepository.existsByUserId(dto.getUserId())) {
            throw new DuplicateUserIdException("회원가입 아이디가 중복됩니다.");
        }
        memberRepository.save(memberMapper.toEntity(dto));
    }
}

package com.example.assignment._0706.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor//ModelAttribute 받는 부분들 밑에 2개 어노테이션 필수
public class BoardWriteRequestDto {
    private String title;
    private String content;
    private String userId;
    private MultipartFile file;
}

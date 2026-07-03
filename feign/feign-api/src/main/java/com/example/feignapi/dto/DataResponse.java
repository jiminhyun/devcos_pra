package com.example.feignapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor //기본생성자 없음
public class DataResponse {
    private Long id;
    private String name;
    private int value;
}

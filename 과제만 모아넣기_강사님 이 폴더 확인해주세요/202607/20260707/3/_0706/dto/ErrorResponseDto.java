package com.example.assignment._0706.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @AllArgsConstructor
public class ErrorResponseDto {
    private String message;
    private int status;
}

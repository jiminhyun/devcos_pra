package com.example.assignment._0703.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Header {
    private String resultCode;   // "00"이면 정상
    private String resultMsg;
}

package com.example.assignment._0703.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Item {
    private String baseDate;
    private String baseTime;
    private String category;    // T1H, REH, PTY ... // 17@@ 쪽 공식 api문서에 기록됨
    private int nx;
    private int ny;
    private String obsrValue;   // 실황값(관측값)
}

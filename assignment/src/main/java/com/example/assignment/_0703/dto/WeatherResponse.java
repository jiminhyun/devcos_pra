package com.example.assignment._0703.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//bean을 이용해 클래스를 내부필드로 선언해서 json형식을 java로 바꾼것, json형식은 api문서 참고

// 이미 준 것
@Getter @Setter @ToString
public class WeatherResponse { private Response response; }


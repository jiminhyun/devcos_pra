package com.example.assignment._0703.controller;
import com.example.assignment._0703.dto.Item;
import com.example.assignment._0703.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherController {// 서비스 로직을 호출할 url 지정

    //[{"baseDate":"20260703","baseTime":"1500","category":"PTY","nx":60,"ny":127,"obsrValue":"0"},{"baseDate":"20260703","baseTime":"1500","category":"REH","nx":60,"ny":127,"obsrValue":"65"},{"baseDate":"20260703","baseTime":"1500","category":"RN1","nx":60,"ny":127,"obsrValue":"0"},{"baseDate":"20260703","baseTime":"1500","category":"T1H","nx":60,"ny":127,"obsrValue":"27.7"},{"baseDate":"20260703","baseTime":"1500","category":"UUU","nx":60,"ny":127,"obsrValue":"1"},{"baseDate":"20260703","baseTime":"1500","category":"VEC","nx":60,"ny":127,"obsrValue":"212"},{"baseDate":"20260703","baseTime":"1500","category":"VVV","nx":60,"ny":127,"obsrValue":"1.6"},{"baseDate":"20260703","baseTime":"1500","category":"WSD","nx":60,"ny":127,"obsrValue":"1.8"}]
    //"강수형태: 없음",
    //  "습도: 65 %",
    //  "1시간 강수량: 0 mm",
    //  "기온: 27.7 ℃",
    //  "풍속: 1.8 m/s"
    // 온 데이터 순서대로
    private final WeatherService weatherService;

    @GetMapping("/weather")
    public List<Item> weather() {
        return weatherService.getCurrentWeather(60, 127); //서울 엑셀 좌표값 참고
    }

    @GetMapping("/weather2")
    public List<String> weather2() {
        return weatherService.getReadableWeather(60, 127); //서울 엑셀 좌표값 참고
    }
}

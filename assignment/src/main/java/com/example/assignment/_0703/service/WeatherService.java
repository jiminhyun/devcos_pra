package com.example.assignment._0703.service;

import com.example.assignment._0703.client.WeatherClient;
import com.example.assignment._0703.dto.Header;
import com.example.assignment._0703.dto.Item;
import com.example.assignment._0703.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WeatherService {//응답을 받기 위한 메서드 로직 구현
    private final WeatherClient weatherClient;

    @Value("${weather.api.key}")
    private String serviceKey;

    public List<Item> getCurrentWeather(int nx, int ny) {
        // 발표시각 계산: 40분 전이면 아직 이번 시각 자료가 없으니 한 시간 전으로 //-매시각 10분 이후 호출 현재는 바뀜 10으로 해도 작동!
        LocalDateTime now = LocalDateTime.now();
        if (now.getMinute() < 40) {
            now = now.minusHours(1);
        }
        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")); //api문서참고
        String baseTime = now.format(DateTimeFormatter.ofPattern("HH")) + "00"; // 예: 1400 //api문서참고 4글자 시+00

        //응답 받기
        WeatherResponse res = weatherClient.getUltraSrtNcst(
                serviceKey, 10, 1, "JSON", baseDate, baseTime, nx, ny);

        // 결과 코드 확인: "00"이 정상. 아니면 메시지를 그대로 알려 준다. api문서에 있음
        Header header = res.getResponse().getHeader();
        if (!"00".equals(header.getResultCode())) {
            throw new RuntimeException("기상청 API 오류: "
                    + header.getResultCode() + " " + header.getResultMsg());
        }

        return res.getResponse().getBody().getItems().getItem();
    }

    public List<String> getReadableWeather(int nx, int ny) {
        List<Item> items = getCurrentWeather(nx, ny);
        List<String> result = new ArrayList<>();

        for (Item item : items) {
            String category = item.getCategory();
            String value = item.getObsrValue();
            switch (category) {
                case "T1H" -> result.add("기온: " + value + " ℃");
                case "REH" -> result.add("습도: " + value + " %");
                case "RN1" -> result.add("1시간 강수량: " + value + " mm");
                case "WSD" -> result.add("풍속: " + value + " m/s");
                case "PTY" -> result.add("강수형태: " + ptyText(value));
                default -> { /* UUU, VVV, VEC 등은 생략 */ }
            }
        }
        return result;
    }

    private String ptyText(String code) {
        return switch (code) {
            case "0" -> "없음";
            case "1" -> "비";
            case "2" -> "비/눈";
            case "3" -> "눈";
            case "5" -> "빗방울";
            case "6" -> "빗방울눈날림";
            case "7" -> "눈날림";
            default  -> "알 수 없음(" + code + ")";
        };
    }
}

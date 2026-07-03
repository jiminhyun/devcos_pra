package com.example.assignment._0703.client;

import com.example.assignment._0703.dto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "weatherClient", url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0")//서비스 url이라고 문서에 있다.
public interface WeatherClient {

    //요청하는 곳 fegin으로 자동으로 spring이 구현해준다.
    @GetMapping("/getUltraSrtNcst")
    WeatherResponse getUltraSrtNcst(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("numOfRows")  int numOfRows,
            @RequestParam("pageNo")     int pageNo,
            @RequestParam("dataType")   String dataType,
            @RequestParam("base_date")  String baseDate,   // 파라미터명은 base_date
            @RequestParam("base_time")  String baseTime,   // base_time
            @RequestParam("nx")         int nx,
            @RequestParam("ny")         int ny
    );//

}

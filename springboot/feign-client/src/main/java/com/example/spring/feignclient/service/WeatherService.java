package com.example.spring.feignclient.service;

import com.example.spring.feignclient.client.WeatherClient;
import com.example.spring.feignclient.dto.weather.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;
    private final ObjectMapper objectMapper;
    @Value("${weather.key}")
    private String serviceKey;

    public WeatherResponse getWeather() {
        int numOfRows = 10;
        int pageNo = 1;
        String dataType = "JSON";
        String baseDate = "20241008"; // YYYYMMDD 형식
        String baseTime = "1400";     // 예: HHMM 형식, 기상청 예보 시간에 맞게
        int nx = 60;                  // 좌표값 (X), 서울
        int ny = 127;                 // 좌표값 (Y), 서울

        String weather = weatherClient.getWeather(
                serviceKey,
                numOfRows,
                pageNo,
                dataType,
                baseDate,
                baseTime,
                nx,
                ny
        );

        return objectMapper.convertValue(weather, WeatherResponse.class);
    }

}

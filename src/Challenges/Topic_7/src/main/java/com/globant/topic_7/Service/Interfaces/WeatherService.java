package com.globant.topic_7.Service.Interfaces;

import com.globant.topic_7.Dto.WeatherRecordDto;

import java.util.List;

public interface WeatherService {
    WeatherRecordDto getLastWeatherRecord();

    List<WeatherRecordDto> getHistory() throws Exception;

    WeatherRecordDto getRecord(long idWeather) throws Exception;

    WeatherRecordDto newRecord(WeatherRecordDto weatherRecord) throws Exception;

    WeatherRecordDto editRecord(WeatherRecordDto weatherRecord) throws Exception;

    WeatherRecordDto deleteRecord(long idWeather) throws Exception;

}

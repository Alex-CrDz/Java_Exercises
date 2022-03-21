package com.globant.topic_7.Dto;

import com.globant.topic_7.Persistence.Model.Location;
import lombok.Builder;
import lombok.Data;
import org.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Data
@Builder
public class WeatherRecordDto {
    private long idWeather;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime insertDate;
    private float[] tempValue;
    private Location location;

    public WeatherRecordDto() {
        this.idWeather = 0;
        this.insertDate = LocalDateTime.now();
        this.tempValue = new float[4];
        this.location = null;
    }

    public WeatherRecordDto(long idWeather, LocalDateTime insertDate, float[] tempValue, Location location) {
        this.idWeather = idWeather;
        this.insertDate = insertDate;
        this.tempValue = tempValue;
        this.location = location;
    }

    public static float[] jsonArrayToFloatArray(String jsonArray) {
        JSONArray ja = new JSONArray(jsonArray);
        float[] array = new float[4];
        for (int i = 0; i < ja.length(); i++) {
            array[i] = ja.getFloat(i);
        }
        return array;
    }

    public static String floatArrayToJsonArray(float[] array) {
        JSONArray ja = new JSONArray(Arrays.asList(array));
        return ja.get(0).toString();
    }
}

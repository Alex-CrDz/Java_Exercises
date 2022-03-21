package com.globant.topic_7.Persistence.Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "WEATHER_RECORDS")
public class WeatherRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idWeather;
    @Column(name = "insertDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime insertDate;
    @Column(name = "tempValue")
    private String tempValue;
    /* ----- RELATIONSHIPS ----- */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location")
    private Location location;

    public WeatherRecord() {
    }

    public WeatherRecord(long idWeather, LocalDateTime insertDate, String tempValue, Location location) {
        this.idWeather = idWeather;
        this.insertDate = insertDate;
        this.tempValue = tempValue;
        this.location = location;
    }
}

package com.globant.topic_7.Persistence.Model;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @Column(name = "idLocation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLocation;
    @Column(name = "latitude")
    @NumberFormat(pattern = "##.####")
    private double latitude;
    @Column(name = "longitude")
    @NumberFormat(pattern = "##.####")
    private double longitude;
    @Column(name = "nameCity")
    private String nameCity;
    @Column(name = "nameState")
    private String nameState;

    public Location() {
    }
}

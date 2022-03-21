package com.globant.topic_7.Service;

import com.globant.topic_7.Dto.WeatherRecordDto;
import com.globant.topic_7.Persistence.Model.WeatherRecord;
import com.globant.topic_7.Persistence.WeatherRepository;
import com.globant.topic_7.Service.Interfaces.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherRepository weatherRepo;

    @Override
    public WeatherRecordDto getLastWeatherRecord() throws RuntimeException {
        AtomicReference<WeatherRecord> record = new AtomicReference<WeatherRecord>();
        weatherRepo.findLast()
                .ifPresentOrElse(dbWeatherRecord -> record.set(dbWeatherRecord), () -> {
                    throw new RuntimeException("No Records");
                });
        WeatherRecordDto dto = WeatherRecordDto.builder()
                .idWeather(record.get().getIdWeather())
                .insertDate(record.get().getInsertDate())
                .tempValue(WeatherRecordDto.jsonArrayToFloatArray(record.get().getTempValue()))
                .location(record.get().getLocation())
                .build();
        return dto;
    }

    @Override
    public List<WeatherRecordDto> getHistory() throws Exception {
        if (weatherRepo.count() == 0) {
            throw new Exception("No records");
        }
        List<WeatherRecord> dbRecords = new ArrayList<WeatherRecord>();
        List<WeatherRecordDto> records = new ArrayList<WeatherRecordDto>();
        for (WeatherRecord item : weatherRepo.findAll()) {
            dbRecords.add(item);
        }
        for (WeatherRecord item : dbRecords) {
            records.add(WeatherRecordDto.builder()
                    .idWeather(item.getIdWeather())
                    .insertDate(item.getInsertDate())
                    .tempValue(WeatherRecordDto.jsonArrayToFloatArray(item.getTempValue()))
                    .location(item.getLocation())
                    .build());
        }
        records.sort(Comparator.comparing(WeatherRecordDto::getInsertDate).reversed());
        return records;
    }

    @Override
    public WeatherRecordDto getRecord(long idWeather) throws RuntimeException {
        AtomicReference<WeatherRecord> record = new AtomicReference<WeatherRecord>();
        weatherRepo.findById(idWeather)
                .ifPresentOrElse(dbWeatherRecord -> record.set(dbWeatherRecord), () -> {
                    throw new RuntimeException("No Records");
                });
        WeatherRecordDto dto = WeatherRecordDto.builder()
                .idWeather(record.get().getIdWeather())
                .insertDate(record.get().getInsertDate())
                .tempValue(WeatherRecordDto.jsonArrayToFloatArray(record.get().getTempValue()))
                .location(record.get().getLocation())
                .build();
        return dto;
    }

    @Override
    public WeatherRecordDto newRecord(WeatherRecordDto weatherRecord) throws Exception {
        if (weatherRepo.existsByInsertDate(weatherRecord.getInsertDate())) {
            throw new Exception("Record already exist");
        }
        WeatherRecord record = WeatherRecord.builder()
                .insertDate(weatherRecord.getInsertDate())
                .tempValue(WeatherRecordDto.floatArrayToJsonArray(weatherRecord.getTempValue()))
                .location(weatherRecord.getLocation())
                .build();
        try {
            record = weatherRepo.save(record);
        } catch (Exception e) {
            throw new Exception("Record not created: " + e.getMessage());
        }
        weatherRecord.setIdWeather(record.getIdWeather());
        return weatherRecord;
    }

    @Override
    public WeatherRecordDto editRecord(WeatherRecordDto weatherRecord) throws Exception {
        if (!weatherRepo.existsByInsertDate(weatherRecord.getInsertDate())){
            throw new Exception("Record not found/not exist");
        }
        WeatherRecord record = WeatherRecord.builder()
                .idWeather(weatherRecord.getIdWeather())
                .insertDate(weatherRecord.getInsertDate())
                .tempValue(WeatherRecordDto.floatArrayToJsonArray(weatherRecord.getTempValue()))
                .location(weatherRecord.getLocation())
                .build();
        try {
            record = weatherRepo.save(record);
        } catch (Exception e) {
            throw new Exception("Record not updated: " + e.getMessage());
        }
        return weatherRecord;
    }

    @Override
    public WeatherRecordDto deleteRecord(long idWeather) throws Exception {
        if (!weatherRepo.existsById(idWeather)) {
            throw new Exception("Record not found/not exist");
        }
        AtomicReference<WeatherRecord> toDelete = new AtomicReference<WeatherRecord>();
        weatherRepo.findById(idWeather)
                .ifPresent(dbRecord -> {
                    toDelete.set(dbRecord);
                    try {
                        weatherRepo.deleteById(toDelete.get().getIdWeather());
                    } catch (Exception e) {
                        throw new RuntimeException("Record not deleted");
                    }
                });
        return WeatherRecordDto.builder()
                .idWeather(toDelete.get().getIdWeather())
                .insertDate(toDelete.get().getInsertDate())
                .tempValue(WeatherRecordDto.jsonArrayToFloatArray(toDelete.get().getTempValue()))
                .location(toDelete.get().getLocation())
                .build();
    }


}

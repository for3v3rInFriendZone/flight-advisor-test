package com.flight.advisor.service.upload.airport;

import com.flight.advisor.service.upload.GetDataFromFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirportDataHandler {

    private static final int CITY_INDEX = 2;

    private final GetDataFromFile getDataFromFile;

    public List<AirportUploadModel> execute(MultipartFile file) {
        log.info("Trying to upload airports from file...");

        return getDataFromFile.execute(file).stream()
                .map(this::toAirportUploadModel)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private AirportUploadModel toAirportUploadModel(String[] current) {
        if (StringUtils.isBlank(current[CITY_INDEX])) {
            return null;
        }

        return AirportUploadModel.builder()
                .id(current[0])
                .name(current[1])
                .city(current[CITY_INDEX])
                .country(current[3])
                .iata(current[4])
                .icao(current[5])
                .latitude(current[6])
                .longitude(current[7])
                .altitude(current[8])
                .timezone(current[9])
                .dst(current[10])
                .databaseTimezone(current[11])
                .type(current[12])
                .source(current[13])
                .build();
    }
}

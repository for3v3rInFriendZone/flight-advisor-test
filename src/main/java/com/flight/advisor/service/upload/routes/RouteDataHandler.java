package com.flight.advisor.service.upload.routes;

import com.flight.advisor.service.upload.GetDataFromFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteDataHandler {

    private final GetDataFromFile getDataFromFile;

    public List<RouteUploadModel> execute(MultipartFile file) {
        log.info("Trying to upload routes from file...");

        return getDataFromFile.execute(file).stream()
                .map(this::toRouteUploadModel)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private RouteUploadModel toRouteUploadModel(String[] current) {
        return RouteUploadModel.builder()
                .airlineCode(current[0])
                .airlineId(current[1])
                .sourceAirportCode(current[2])
                .sourceAirportId(current[3])
                .destinationAirportCode(current[4])
                .destinationAirportId(current[5])
                .codeshare(current[6])
                .stops(current[7])
                .equipment(current[8])
                .price(current[9])
                .build();
    }
}

package com.flight.advisor.api;

import com.flight.advisor.service.airport.CreateAirports;
import com.flight.advisor.service.route.CreateRoutes;
import com.flight.advisor.service.upload.airport.AirportDataHandler;
import com.flight.advisor.service.upload.airport.AirportUploadModel;
import com.flight.advisor.service.upload.routes.RouteDataHandler;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadApi {

    private final AirportDataHandler airportDataHandler;
    private final CreateAirports createAirports;
    private final RouteDataHandler routeDataHandler;
    private final CreateRoutes createRoutes;

    @PostMapping("/airports")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@userTypePermission.hasAny('ADMIN')")
    public void uploadAirports(@RequestParam MultipartFile file) {
        List<AirportUploadModel> uploadedAirports = airportDataHandler.execute(file);

        createAirports.execute(uploadedAirports);
    }

    @PostMapping("/routes")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@userTypePermission.hasAny('ADMIN')")
    public void uploadRoutes(@RequestParam MultipartFile file) {
        List<RouteUploadModel> uploadedAirports = routeDataHandler.execute(file);

        createRoutes.execute(uploadedAirports);
    }
}

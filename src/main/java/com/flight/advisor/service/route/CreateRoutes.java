package com.flight.advisor.service.route;

import com.flight.advisor.service.upload.routes.RouteUploadModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateRoutes {

    private final SaveRoute saveRoute;

    public void execute(List<RouteUploadModel> routes) {
        for (RouteUploadModel route : routes) {
            saveRoute.execute(route);
        }

        log.info("Routes upload finished successfully.");
    }
}

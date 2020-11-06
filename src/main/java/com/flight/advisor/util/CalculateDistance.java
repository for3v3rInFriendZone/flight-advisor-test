package com.flight.advisor.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class CalculateDistance {

    public static BigDecimal calculate(double lat1, double lat2, double lon1,
                                      double lon2, double alt1, double alt2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        double height = alt1 - alt2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        final double result = Math.sqrt(distance) / 1000;

        return BigDecimal.valueOf(result)
                .setScale(0, RoundingMode.HALF_UP);
    }
}

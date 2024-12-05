package com.aditya.project.uber.uberApp.services.impl;


import com.aditya.project.uber.uberApp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class DistanceServiceOlaImpl implements DistanceService {

    private static final String OLA_BASE_URL="https://api.olamaps.io/routing/v1/distanceMatrix";
    @Value("${API_KEY}")
    private String API_KEY;
    @Override
    public double calculateDistance(Point p1, Point p2) {
        try{
            String URI="?origins="+p1.getX()+","+p1.getY()+"|"+"&destinations="+p2.getX()+","+p2.getY()+"&mode=driving&api_key="+API_KEY;
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(API_KEY);  // Add Bearer token authorization
            headers.setContentType(MediaType.APPLICATION_JSON);
            OLAResponseDto olaResponseDto=RestClient.builder()
                    .baseUrl(OLA_BASE_URL)
                    .build()
                    .get()
                    .uri(URI)
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .retrieve()
                    .body(OLAResponseDto.class);
            assert olaResponseDto != null;
            return olaResponseDto.getRows().getFirst().getElements().getFirst().getDistance() /1000.0;
        }
        catch(Exception e){
            throw new RuntimeException("Error calculating distance from OLA , "+ e.getMessage());
        }
    }
}

@Data
class OLAResponseDto{
    private List<OLARows> rows;
}

@Data
class OLARows{
    List<OLAElements> elements;
}

@Data
class OLAElements{
    private Double distance;
}

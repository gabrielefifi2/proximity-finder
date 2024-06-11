package com.fabrick.proximity_finder.service.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AviationWeatherService {

    private final RestTemplate restTemplate;
    private final String airportInfoBaseUrl;
    private final String stationInfoBaseUrl;

    @Autowired
    public AviationWeatherService(RestTemplate restTemplate,
                                  @Value("${aviationweather.airport-info.api.base-url}") String airportInfoBaseUrl,
                                  @Value("${aviationweather.station-info.api.base-url}") String stationInfoBaseUrl) {
        this.restTemplate = restTemplate;
        this.airportInfoBaseUrl = airportInfoBaseUrl;
        this.stationInfoBaseUrl = stationInfoBaseUrl;
    }

    public String getAirportInfo(String airportId, String bbox) {
        return callService(airportInfoBaseUrl, airportId, bbox);
    }

    public String getStationInfo(String stationId, String bbox) {
        return callService(stationInfoBaseUrl, stationId, bbox);
    }

    private String callService(String baseUrl, String id, String bbox) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);

        if (id != null && !id.isEmpty()) {
            builder.queryParam("ids", id);
        }

        if (bbox != null && !bbox.isEmpty()) {
            builder.queryParam("bbox", bbox);
        }

        builder.queryParam("format", "json");

        String url = builder.toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}

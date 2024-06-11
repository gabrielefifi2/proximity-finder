package com.fabrick.proximity_finder.controller;

import com.fabrick.proximity_finder.model.AirportData;
import com.fabrick.proximity_finder.model.StationData;
import com.fabrick.proximity_finder.service.ProximityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fabrick/v1.0")
public class ProximityController {

    private final ProximityService proximityService;

    @Autowired
    public ProximityController(ProximityService proximityService) {
        this.proximityService = proximityService;
    }

    @Cacheable(value = "airportInfoCache", key = "#airportId + ':' + #closestBy")
    @GetMapping("/airports/{airportId}/stations")
    public List<StationData> getAirportInfo(@PathVariable String airportId,
                                            @RequestParam (required = false, defaultValue = "0.0") Double closestBy) {

        return proximityService.findAirportProximityInfo(airportId, closestBy);
    }

    @Cacheable(value = "stationInfoCache", key = "#stationId + ':' + #closestBy")
    @GetMapping("/stations/{stationId}/airports")
    public List<AirportData> getStationInfo(@PathVariable String stationId,
                                            @RequestParam (required = false, defaultValue = "0.0") Double closestBy) {

        return proximityService.findStationProximityInfo(stationId, closestBy);
    }
}

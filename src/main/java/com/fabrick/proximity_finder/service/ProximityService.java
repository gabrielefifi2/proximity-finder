package com.fabrick.proximity_finder.service;

import com.fabrick.proximity_finder.model.*;
import com.fabrick.proximity_finder.service.external.AviationWeatherService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProximityService {

    private static final Type AIRPORT_DATA_LIST_TYPE = new TypeToken<List<AirportData>>(){}.getType();
    private static final Type STATION_DATA_LIST_TYPE = new TypeToken<List<StationData>>(){}.getType();

    private final AviationWeatherService aviationWeatherService;

    @Autowired
    public ProximityService(AviationWeatherService aviationWeatherService) {
        this.aviationWeatherService = aviationWeatherService;
    }

    public List<StationData> findAirportProximityInfo(String airportId, Double closestBy) {
        List<StationData> closeStationsList = new ArrayList<>();
        List<AirportData> airportData = callAviationWeatherService(airportId, "", AIRPORT_DATA_LIST_TYPE);

        if (airportData != null && !airportData.isEmpty()) {
            String bbox = generateBbox(airportData.get(0), closestBy);

            if (!bbox.isEmpty()) {
                closeStationsList = callAviationWeatherService("", bbox, STATION_DATA_LIST_TYPE);
            }
        }
        return closeStationsList;
    }

    public List<AirportData> findStationProximityInfo(String stationId, Double closestBy) {
        List<AirportData> closeAirportsList = new ArrayList<>();
        List<StationData> stationData = callAviationWeatherService(stationId, "", STATION_DATA_LIST_TYPE);

        if (stationData != null && !stationData.isEmpty()) {
            String bbox = generateBbox(stationData.get(0), closestBy);

            if (!bbox.isEmpty()) {
                closeAirportsList = callAviationWeatherService("", bbox, AIRPORT_DATA_LIST_TYPE);
            }
        }
        return closeAirportsList;
    }

    private <T> List<T> callAviationWeatherService(String id, String bbox, Type type) {
        String dataStr = "";

        if (type.equals(AIRPORT_DATA_LIST_TYPE)) {
            dataStr = aviationWeatherService.getAirportInfo(id, bbox);
        } else if (type.equals(STATION_DATA_LIST_TYPE)) {
            dataStr = aviationWeatherService.getStationInfo(id, bbox);
        }
        return new Gson().fromJson(dataStr, type);
    }

    private String generateBbox(ObjectData objectData, Double closestBy) {
        double lat0, lon0, lat1, lon1;
        lat0 = objectData.getLatitude() - closestBy;
        lon0 = objectData.getLongitude() - closestBy;
        lat1 = objectData.getLatitude() + closestBy;
        lon1 = objectData.getLongitude() + closestBy;

        return String.format("%s,%s,%s,%s", lat0, lon0, lat1, lon1);
    }
}

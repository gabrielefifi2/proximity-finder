package com.fabrick.proximity_finder.service;

import com.fabrick.proximity_finder.service.external.AviationWeatherService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProximityServiceTest {

    private final AviationWeatherService aviationWeatherService = mock(AviationWeatherService.class);
    private final ProximityService proximityService = new ProximityService(aviationWeatherService);

    @Test
    public void proximityServiceOKTest() {
        when(aviationWeatherService.getAirportInfo(anyString(),anyString()))
                .thenReturn(getAirportInfo());
        when(aviationWeatherService.getStationInfo(anyString(),anyString()))
                .thenReturn(getStationInfo());
        assertFalse((proximityService.findAirportProximityInfo("KMCI", 10.0)).isEmpty());
    }

    @Test
    public void proximityServiceKOTest() {
        when(aviationWeatherService.getAirportInfo(anyString(),anyString()))
                .thenReturn("{[}");
        assertThrows(Exception.class, () -> proximityService.findAirportProximityInfo("111", 0.0));
    }

    private String getAirportInfo() {
        return """
                [
                  {
                    "id": "9354",
                    "icaoId": "KMCI",
                    "iataId": "MCI",
                    "faaId": "MCI",
                    "name": "KANSAS CITY/KANSAS CITY INTL ",
                    "state": "MO",
                    "country": "US",
                    "source": "FAA",
                    "type": "ARP",
                    "lat": 39.2976,
                    "lon": -94.7139,
                    "elev": 313,
                    "magdec": "02E",
                    "owner": "P",
                    "runways": [
                      {
                        "id": "01L/19R",
                        "dimension": "10801x150",
                        "surface": "A",
                        "alignment": 13
                      },
                      {
                        "id": "01R/19L",
                        "dimension": "9500x150",
                        "surface": "C",
                        "alignment": 13
                      },
                      {
                        "id": "09/27",
                        "dimension": "9501x150",
                        "surface": "A",
                        "alignment": 96
                      }
                    ],
                    "rwyNum": "3",
                    "rwyLength": "L",
                    "rwyType": "C",
                    "services": "S",
                    "tower": "T",
                    "beacon": "B",
                    "operations": "107035",
                    "passengers": "9860",
                    "freqs": "LCL/P,128.2;D-ATIS,128.375",
                    "priority": "1"
                  }
                ]""";
    }

    private String getStationInfo() {
        return """
                [
                  {
                    "icaoId": "45005",
                    "iataId": "-",
                    "faaId": "-",
                    "wmoId": "-",
                    "lat": 41.677,
                    "lon": -82.398,
                    "elev": 174,
                    "site": "Cleveland 28NW",
                    "state": "--",
                    "country": "US",
                    "priority": 6
                  },
                  {
                    "icaoId": "45007",
                    "iataId": "-",
                    "faaId": "-",
                    "wmoId": "-",
                    "lat": 42.674,
                    "lon": -87.026,
                    "elev": 176,
                    "site": "South Lake Michigan",
                    "state": "--",
                    "country": "US",
                    "priority": 4
                  },
                  {
                    "icaoId": "45008",
                    "iataId": "-",
                    "faaId": "-",
                    "wmoId": "-",
                    "lat": 44.283,
                    "lon": -82.416,
                    "elev": 177,
                    "site": "South Lake Huron",
                    "state": "--",
                    "country": "US",
                    "priority": 4
                  }
                  ]""";
    }
}

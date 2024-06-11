package com.fabrick.proximity_finder.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AirportData extends ObjectData{

    private String name;
}

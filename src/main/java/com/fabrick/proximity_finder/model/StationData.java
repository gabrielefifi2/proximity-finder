package com.fabrick.proximity_finder.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StationData extends ObjectData{

    private String site;
}

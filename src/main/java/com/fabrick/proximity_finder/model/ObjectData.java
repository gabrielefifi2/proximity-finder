package com.fabrick.proximity_finder.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class ObjectData {

    @SerializedName("icaoId")
    private String id;

    private String state;
    private String country;

    @SerializedName("lat")
    private Double latitude;

    @SerializedName("lon")
    private Double longitude;

    @SerializedName("elev")
    private Integer elevation;
}

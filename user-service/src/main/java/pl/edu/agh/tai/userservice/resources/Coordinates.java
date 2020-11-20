package pl.edu.agh.tai.userservice.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {

    private final String latitude;
    private final String longitude;

    public Coordinates(
            @JsonProperty("latitude") String latitude,
            @JsonProperty("longitude") String longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates(
            @JsonProperty("latitude") double latitude,
            @JsonProperty("longitude") double longitude) {

        this.latitude = String.valueOf(latitude);
        this.longitude = String.valueOf(longitude);
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

}

package pl.edu.agh.tai.userservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinatesRequestBody {
    private double latitude;
    private double longitude;

    public CoordinatesRequestBody( @JsonProperty("Latitude") double latitude, @JsonProperty("Longitude") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CoordinatesRequestBody() {
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}


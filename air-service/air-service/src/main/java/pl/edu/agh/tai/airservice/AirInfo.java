package pl.edu.agh.tai.airservice;

public class AirInfo {

    private String description;
    private String advice;
    private String color;
    private double latitude;
    private double longitude;

    public AirInfo() {
    }

    public AirInfo(String description, String advice, String color, double latitude, double longitude) {
        this.description = description;
        this.advice = advice;
        this.color = color;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getAdvice() {
        return advice;
    }

    public String getColor() {
        return color;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

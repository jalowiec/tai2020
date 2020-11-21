package pl.edu.agh.tai.airservice;

public class AirInfo {

    private String description;
    private String advice;
    private String color;

    public AirInfo() {
    }

    public AirInfo(String description, String advice, String color) {
        this.description = description;
        this.advice = advice;
        this.color = color;
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
}

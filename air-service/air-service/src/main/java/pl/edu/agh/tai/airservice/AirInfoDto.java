package pl.edu.agh.tai.airservice;

public class AirInfoDto {

    private String description;
    private String advice;
    private int color;

    public AirInfoDto() {
    }

    public AirInfoDto(String description, String advice, int color) {
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

    public int getColor() {
        return color;
    }
}

package pl.edu.agh.tai.imagesservice;

public class ImageDto {
    private String url;

    public ImageDto() {
    }

    public ImageDto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

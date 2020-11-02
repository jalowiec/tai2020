package pl.edu.agh.tai.imagesservice;

import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageDto ImageToImageDto(final Image Image){
        return new ImageDto(Image.getUrl());
    }
    
}

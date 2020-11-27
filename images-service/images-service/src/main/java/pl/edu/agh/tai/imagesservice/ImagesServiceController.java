package pl.edu.agh.tai.imagesservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users/{id}")
public class ImagesServiceController {

    @Autowired
    private ImageMapper ImageMapper;


    //TODO sprawdzenie konwencji

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/images/image")
    @HystrixCommand(fallbackMethod = "getDefaultImage")
    public ImageDto getImage(){
        return ImageMapper.ImageToImageDto(getExternalImage());
    }

    private Image getExternalImage(){
        final String uri = "https://api.nasa.gov/planetary/apod?api_key=1jYeaDlED5B6FWRrOfiiVlEKvzOOpn8Q4ijXgJIh";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        String imageUrl = jsonObject.get("hdurl").getAsString();

        logger.info("{}", imageUrl + ":" + imageUrl);

        return new Image(imageUrl);
    }

    private ImageDto getDefaultImage(){

        logger.info("{}", "default Image");

        return new ImageDto("https://cdn.spacetelescope.org/archives/images/wallpaper2/heic2007a.jpg");
    }
}

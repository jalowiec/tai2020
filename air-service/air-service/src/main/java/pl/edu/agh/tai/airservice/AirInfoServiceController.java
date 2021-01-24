package pl.edu.agh.tai.airservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users/{id}")
public class AirInfoServiceController {

    @Autowired
    private AirInfoMapper airInfoMapper;


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(method = RequestMethod.GET, value = "//air-infos/air-info", produces= MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(fallbackMethod = "getDefaultAirInfo")
    public AirInfoDto getAirInfo(@PathVariable(value = "id") int pathUserId){

        final String uri = "http://localhost:8080//users/" + pathUserId +"/coordinates/getCoordinates";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        int userId = jsonObject.get("userID").getAsInt();
        double longitude = jsonObject.get("longitude").getAsDouble();
        double latitude = jsonObject.get("latitude").getAsDouble();
        return airInfoMapper.airInfoToAirInfoDto(getExternalAirInfo(userId, longitude, latitude));
    }

    private AirInfo getExternalAirInfo(int userId, double longitude, double latitude){
        AirInfo airInfo;
        final String uri = "https://airapi.airly.eu/v2/measurements/point?lat=" + latitude + "&lng=" + longitude + "&apikey=lG5RZe9W6Adcnt7MYudggTpWbHyoWvkM";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject().getAsJsonObject("current").getAsJsonArray("indexes").get(0).getAsJsonObject();

        String airDescription = jsonObject.get("description").getAsString();
        String airAdvice = jsonObject.get("advice").getAsString();
        String airColor = jsonObject.get("color").getAsString();

        if(airDescription != null && airAdvice != null && airColor != null) {
            airInfo = new AirInfo(airDescription, airAdvice, airColor, latitude, longitude);
        } else {
            airInfo = airInfoMapper.airInfoDtoToAirInfo(getDefaultAirInfo(userId));
        }

        logger.info("{}", airDescription + ":" + airAdvice + ":" + airColor);

        return airInfo;
    }

    private AirInfoDto getDefaultAirInfo(int id){

        logger.info("{}", "default Air");

        return new AirInfoDto("No current info", "Check outside", "#FF0000", 0.0, 0.0);
    }



}

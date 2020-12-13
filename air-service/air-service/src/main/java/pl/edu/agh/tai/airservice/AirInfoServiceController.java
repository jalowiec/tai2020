package pl.edu.agh.tai.airservice;

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
public class AirInfoServiceController {

    @Autowired
    private AirInfoMapper airInfoMapper;


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/air-infos/air-info")
    @HystrixCommand(fallbackMethod = "getDefaultAirInfo")
    public AirInfoDto getAirInfo(){
        return airInfoMapper.airInfoToAirInfoDto(getExternalAirInfo());
    }

    private AirInfo getExternalAirInfo(){
        AirInfo airInfo;
        final String uri = "https://airapi.airly.eu/v2/measurements/point?lat=50.067879&lng=19.912863&apikey=lG5RZe9W6Adcnt7MYudggTpWbHyoWvkM";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject().getAsJsonObject("current").getAsJsonArray("indexes").get(0).getAsJsonObject();

        String airDescription = jsonObject.get("description").getAsString();
        String airAdvice = jsonObject.get("advice").getAsString();
        String airColor = jsonObject.get("color").getAsString();

        if(airDescription != null && airAdvice != null && airColor != null) {
            airInfo = new AirInfo(airDescription, airAdvice, airColor);
        } else {
            airInfo = airInfoMapper.airInfoDtoToAirInfo(getDefaultAirInfo());
        }

        logger.info("{}", airDescription + ":" + airAdvice + ":" + airColor);

        return airInfo;
    }

    private AirInfoDto getDefaultAirInfo(){

        logger.info("{}", "default Air");

        return new AirInfoDto("No current info", "Check outside", "#FF0000");
    }



}

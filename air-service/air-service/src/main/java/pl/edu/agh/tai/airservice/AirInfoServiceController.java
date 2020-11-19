package pl.edu.agh.tai.airservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/air-manager/users/{id}")
@CrossOrigin(origins = "*")
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
        final String airlyKey = "lG5RZe9W6Adcnt7MYudggTpWbHyoWvkM";
        final String uri = "https://airapi.airly.eu/v2/measurements/point?lat=50.067879&lng=19.912863&apikey=lG5RZe9W6Adcnt7MYudggTpWbHyoWvkM";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        JsonObject jsonQuoteObject = jsonObject.getAsJsonArray("quotes").get(0).getAsJsonObject();
        String quote = jsonQuoteObject.get("quote").getAsString();
        String author = "Unknown";
        if(jsonQuoteObject.get("author") != null){
            author = jsonQuoteObject.get("author").getAsString();
        }

        logger.info("{}", author + ":" + quote.substring(0, 5));

        return new AirInfo("No current info", "Check outside", 0XFF0000);
    }

    private AirInfoDto getDefaultAirInfo(){

        logger.info("{}", "default author" + ":" + "default quote");

        return new AirInfoDto("No current info", "Check outside", 0XFF0000);
    }



}

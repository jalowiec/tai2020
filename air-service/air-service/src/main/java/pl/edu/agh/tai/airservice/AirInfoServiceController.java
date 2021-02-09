package pl.edu.agh.tai.airservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AirInfoServiceController {

    @Autowired
    private AirInfoMapper airInfoMapper;


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(method = RequestMethod.GET, value = "/air-info", produces= MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(fallbackMethod = "getDefaultAirInfo")
    public AirInfoDto getAirInfo(@AuthenticationPrincipal Jwt jwt){
        System.out.println("#########1");
        final String uri = "http://localhost:8080/coordinates";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJORDhWZkwtb2luTjN1RmRIYXlhbjlGWG1wVTk2WTgwZDdQSmthUzJYUDc4In0.eyJleHAiOjE2MTI5MzkxOTYsImlhdCI6MTYxMjkwMzIzMCwiYXV0aF90aW1lIjoxNjEyOTAzMTk2LCJqdGkiOiIwYjU4NzQ1MC0yYWJkLTRjN2MtOGNhZi02OGUxMDlhYzViZjEiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvdGFpIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjUzNmIyODhjLTc0ODgtNDE0YS1iZTI3LWZjMDI2Y2UzZDk5MyIsInR5cCI6IkJlYXJlciIsImF6cCI6InRhaSIsInNlc3Npb25fc3RhdGUiOiI5MmFiYmE3OC00YThiLTRhOGItYTVmMS1lYmUyM2Q5MTJmNWEiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0YWkxIn0.kAvQx9IvSBEOXXhcLvaz84gOx3t-VeoHDr86e0hHTVPIblyKECX8pCiqZmpa1J--CUpfMuEFsF9_f7DreMo9EaOVaQB9HCnULMbHyEXOhlRB-YoWtRJstQz0Y0fk1wHf3FTormdvL9GHwj2GJcMXkPsFNQbEIcAY6bJqNoWbxxhUFJRW1Z_tekyGmNOby6_q9cg-DA2yqueJ86hWca_TC-Eh1Q5paagfduIS6qw_OC05_36hc-oM6x_bqkxTdLAxp-xTgb5r3M_68il9LAnNBpR9IKR2Ll1a--f0pnBiWQ9JkYpGq1WDVVG26PAY9ZW6GCf0AWoBLb4U7lfvAxsxdA");
        System.out.println("#########2");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class, "");
        //String result = restTemplate.getForObject(uri, String.class);
        System.out.println("#########3");
        System.out.println("#########RESULT: " + response.getBody());
        JsonObject jsonObject = JsonParser.parseString("test").getAsJsonObject();
        int userId = jsonObject.get("userID").getAsInt();
        double longitude = jsonObject.get("longitude").getAsDouble();
        double latitude = jsonObject.get("latitude").getAsDouble();
        return airInfoMapper.airInfoToAirInfoDto(getExternalAirInfo(userId, longitude, latitude));
    }

    private AirInfo getExternalAirInfo(int userId, double longitude, double latitude){
        AirInfo airInfo = null;
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
           // airInfo = airInfoMapper.airInfoDtoToAirInfo(getDefaultAirInfo(userId));
        }

        logger.info("{}", airDescription + ":" + airAdvice + ":" + airColor);

        return airInfo;
    }

    private AirInfoDto getDefaultAirInfo(@AuthenticationPrincipal Jwt jwt){

        logger.info("{}", "default Air");

        return new AirInfoDto("No current info", "Check outside", "#FF0000", 0.0, 0.0);
    }

    private int getUserIdFromKeycloakUser(String keyCloakUser){
        Map<String, Integer> map = new HashMap<>();
        map.put("tai1", 1);
        map.put("tai2", 2);
        map.put("tai3", 3);
        return map.get(keyCloakUser);
    }


}

package pl.edu.agh.tai.airservice;

import org.springframework.stereotype.Component;

@Component
public class AirInfoMapper {

    public AirInfoDto airInfoToAirInfoDto(final AirInfo airInfo){
        return new AirInfoDto(airInfo.getDescription(), airInfo.getAdvice(), airInfo.getColor());
    }





}

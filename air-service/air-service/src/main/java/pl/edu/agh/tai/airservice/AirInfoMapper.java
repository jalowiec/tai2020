package pl.edu.agh.tai.airservice;

import org.springframework.stereotype.Component;

@Component
public class AirInfoMapper {

    public AirInfoDto airInfoToAirInfoDto(final AirInfo airInfo){
        return new AirInfoDto(airInfo.getDescription(), airInfo.getAdvice(), airInfo.getColor(), airInfo.getLatitude(), airInfo.getLongitude());
    }

    public AirInfo airInfoDtoToAirInfo(final AirInfoDto airInfoDto){
        return new AirInfo(airInfoDto.getDescription(), airInfoDto.getAdvice(), airInfoDto.getColor(), airInfoDto.getLatitude(), airInfoDto.getLongitude());
    }


}

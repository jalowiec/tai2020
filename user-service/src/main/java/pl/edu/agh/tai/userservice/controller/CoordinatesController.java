package pl.edu.agh.tai.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.*;
import pl.edu.agh.tai.userservice.repository.CoordinatesRepository;
import pl.edu.agh.tai.userservice.repository.HobbyRepository;
import pl.edu.agh.tai.userservice.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CoordinatesController {

    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/coordinates", produces= MediaType.APPLICATION_JSON_VALUE)
    public Coordinates getCoordinates(@AuthenticationPrincipal Jwt jwt){
        return coordinatesRepository.findByUserID(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
    }


    @PostMapping(value = "/coordinates")
    public void addCoordinates(@AuthenticationPrincipal Jwt jwt,
                               @RequestBody CoordinatesRequestBody coordinatesRequestBody)  throws Exception{
        Coordinates coordinatesFromRepository = coordinatesRepository.findByUserID(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
        if(coordinatesFromRepository == null){
            Coordinates coordinates = new Coordinates();
            coordinates.setLatitude(coordinatesRequestBody.getLatitude());
            coordinates.setLongitude(coordinatesRequestBody.getLongitude());
            coordinates.setUserID(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
            coordinatesRepository.save(coordinates);

        } else {
            throw new Exception("Uzytkownik ma juz zdefiniowane wspolrzedne w bazie");
        }
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/coordinates")
    public Coordinates updateCoordinates(@RequestBody CoordinatesRequestBody coordinatesRequestBody, @AuthenticationPrincipal Jwt jwt) throws Exception {
        Coordinates coordinatesFromRepository = coordinatesRepository.findByUserID(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
        if(coordinatesFromRepository != null){
            coordinatesFromRepository.setLatitude(coordinatesRequestBody.getLatitude());
            coordinatesFromRepository.setLongitude(coordinatesRequestBody.getLongitude());
            coordinatesFromRepository.setUserID(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));


        } else {
            throw new Exception("Nie znaleziono wspolrzednych do zmiany");
        }
        return coordinatesRepository.save(coordinatesFromRepository);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/coordinates")
    public void deleteCoordinates(@AuthenticationPrincipal Jwt jwt) throws Exception {
        Coordinates coordinatesFromRepository = coordinatesRepository.findByUserID(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
        if(coordinatesFromRepository != null){
            coordinatesRepository.delete(coordinatesFromRepository);
        } else {
            throw new Exception("Nie znaleziono wspolrzednych do usuniecia");
        }
    }


    private int getUserIdFromKeycloakUser(String keyCloakUser){
        Map<String, Integer> map = new HashMap<>();
        map.put("tai1", 1);
        map.put("tai2", 2);
        map.put("tai3", 3);
        return map.get(keyCloakUser);
    }


}
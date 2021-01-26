package pl.edu.agh.tai.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.CoordinatesRequestBody;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.HobbyRequestBody;
import pl.edu.agh.tai.userservice.repository.CoordinatesRepository;
import pl.edu.agh.tai.userservice.repository.HobbyRepository;
import pl.edu.agh.tai.userservice.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}")
public class CoordinatesController {

    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/coordinates", produces= MediaType.APPLICATION_JSON_VALUE)
    public Coordinates getCoordinates(@PathVariable(value = "userId") int userID){
        return coordinatesRepository.findByUserID(userID);
    }

    @PostMapping(value = "/coordinates")
    public void addCoordinates(@PathVariable(value = "userId") int userID,
                               @RequestBody CoordinatesRequestBody coordinatesRequestBody){

        coordinatesRepository.save(new Coordinates(userID, coordinatesRequestBody.getLatitude(), coordinatesRequestBody.getLongitude()));
    }




}
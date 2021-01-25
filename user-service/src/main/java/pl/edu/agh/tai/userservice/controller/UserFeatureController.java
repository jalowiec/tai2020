package pl.edu.agh.tai.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.HobbyRepository;
import pl.edu.agh.tai.userservice.repository.UserRepository;
import pl.edu.agh.tai.userservice.service.CoordinatesService;
import pl.edu.agh.tai.userservice.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/users/{userID}")
public class UserFeatureController {

    private final UserService userService;
    @Autowired
    private CoordinatesService coordinatesService;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private UserRepository userRepository;

    public UserFeatureController(UserService userService) {
        this.userService = userService;
    }
    public UserFeatureController(){
        userService = new UserService();
    }

    // User CRUD
    @RequestMapping(value = "/getProfile")
    public User getProfile(@PathVariable(value = "userID") int userID){
        return userService.getProfile(userID);
    }

    @PostMapping(value = "/users/addProfile")
    public void addProfile(@RequestBody User user){
        userService.addProfile(user);
    }

    @PostMapping(value = "/updateProfile")
    public void updateProfile(@PathVariable(value = "userID") int userID,
                              @RequestBody User user){
        userService.updateProfile(user, userID);
    }

    @DeleteMapping(value = "/delete")
    public void deleteProfile(@PathVariable(value = "userID") int userID){
        userService.deleteProfile(userID);
    }

    //coordinates CRUD
    @RequestMapping(method = RequestMethod.GET, value = "/coordinates/getCoordinates", produces= MediaType.APPLICATION_JSON_VALUE)
    public Coordinates getCoordinates(@PathVariable(value = "userID") int userID){
        return coordinatesService.getCoordinates(userID);
    }

    @PostMapping(value = "/coordinates/addCoordinates")
    public void addCoordinate(@PathVariable(value = "userID") int userID,
                              @RequestBody Coordinates coordinates){
        userService.addCoordinate(coordinates, userID);
    }

    @PostMapping(value = "/coordinates/updateCoordinates")
    public void updateCoordinates(@PathVariable(value = "userID") int userID,
                                  @RequestBody Coordinates coordinates){
        userService.updateCoordinates(coordinates, userID);
    }

    @DeleteMapping(value = "/coordinates/delete")
    public void deleteCoordinates(@PathVariable(value = "userID") int userID) {
        userService.deleteCoordinates(userID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hobbies", produces= MediaType.APPLICATION_JSON_VALUE)
    //public List<Hobby> getHobbies(@PathVariable(value = "userID") int userID){
   //     return userService.getHobbies(userID);
   // }
    public List<Hobby> getHobbies(@PathVariable(value = "userID") int userID){
        return hobbyRepository.findByHobbyOwner(userID);
    }





}
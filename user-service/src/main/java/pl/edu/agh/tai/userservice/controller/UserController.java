package pl.edu.agh.tai.userservice.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/user-manager")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    public UserController(){
        userService = new UserService();
    }

    // User CRUD
    @RequestMapping(value = "/users/{userID}/getProfile")
    public User getProfile(@PathVariable(value = "userID") int userID){
        return userService.getProfile(userID);
    }

    @PostMapping(value = "/users/addProfile")
    public void addProfile(@RequestBody User user){
        userService.addProfile(user);
    }

    @PostMapping(value = "/users/{userID}/updateProfile")
    public void updateProfile(@PathVariable(value = "userID") int userID,
                              @RequestBody User user){
        userService.updateProfile(user, userID);
    }

    @DeleteMapping(value = "/users/{userID}/delete")
    public void deleteProfile(@PathVariable(value = "userID") int userID){
        userService.deleteProfile(userID);
    }

    //coordinates CRUD
    @RequestMapping(value = "/users/{userID}/coordinates/getCoordinates")
    public Coordinates getCoordinates(@PathVariable(value = "userID") int userID){
        return userService.getCoordinates(userID);
    }

    @PostMapping(value = "/users/{userID}/coordinates/addCoordinates")
    public void addCoordinate(@PathVariable(value = "userID") int userID,
                              @RequestBody Coordinates coordinates){
        userService.addCoordinate(coordinates, userID);
    }

    @PostMapping(value = "/users/{userID}/coordinates/updateCoordinates")
    public void updateCoordinates(@PathVariable(value = "userID") int userID,
                                  @RequestBody Coordinates coordinates){
        userService.updateCoordinates(coordinates, userID);
    }

    @DeleteMapping(value = "/users/{userID}/coordinates/delete")
    public void deleteCoordinates(@PathVariable(value = "userID") int userID) {
        userService.deleteCoordinates(userID);
    }

    @RequestMapping(value = "/users/{userID}/hobbies")
    public List<Hobby> getHobbies(@PathVariable(value = "userID") int userID){
        return userService.getHobbies(userID);
    }


}
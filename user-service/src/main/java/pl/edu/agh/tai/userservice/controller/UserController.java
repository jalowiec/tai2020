package pl.edu.agh.tai.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.HobbyRepository;
import pl.edu.agh.tai.userservice.repository.UserRepository;
import pl.edu.agh.tai.userservice.service.UserService;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/users")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable(value = "userId") int userID){
        return userRepository.findById(userID);
    }

}
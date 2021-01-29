package pl.edu.agh.tai.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.HobbyRequestBody;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.HobbyRepository;
import pl.edu.agh.tai.userservice.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}")
public class HobbyController {

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/hobby")
    public void addHobby(@RequestBody HobbyRequestBody hobbyRequestBody, @PathVariable(value = "userId") int userID){
        Hobby hobbyToAdd = new Hobby(userRepository.findById(userID), hobbyRequestBody.getName(), hobbyRequestBody.getDescription());
        hobbyRepository.save(hobbyToAdd);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hobby", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Hobby> getHobbies(@PathVariable(value = "userId") int userID){
        return hobbyRepository.findByHobbyOwner(userID);
    }






}
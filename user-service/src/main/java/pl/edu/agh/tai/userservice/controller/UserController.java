package pl.edu.agh.tai.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.HobbyRepository;
import pl.edu.agh.tai.userservice.repository.UserRepository;
import pl.edu.agh.tai.userservice.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/user")
    public void addUser(@RequestBody User user, @AuthenticationPrincipal Jwt jwt) throws Exception {
       User userFromRepository = userRepository.findById(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
       if(userFromRepository == null){
           user.setUser_id(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
            userRepository.save(user);

       } else {
           throw new Exception("Uzytkownik juz istnieje w bazie");
       }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public User updateUser(@RequestBody User user, @AuthenticationPrincipal Jwt jwt) throws Exception {
        User userFromRepository = userRepository.findById(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
        if(userFromRepository != null) {
            userFromRepository.setFirst_name(user.getFirst_name());
            userFromRepository.setSurname(user.getSurname());
            userFromRepository.setEmail(user.getEmail());
        } else{
            throw new Exception("Nie znaleziono w bazie uzytkownika do zmiany");
        }
        return userRepository.save(userFromRepository);

    }


    @RequestMapping(method = RequestMethod.GET, value = "/user", produces= MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@AuthenticationPrincipal Jwt jwt){
        return userRepository.findById(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
    }


    private int getUserIdFromKeycloakUser(String keyCloakUser){
        Map<String, Integer> map = new HashMap<>();
        map.put("tai1", 1);
        map.put("tai2", 2);
        map.put("tai3", 3);
        return map.get(keyCloakUser);
    }

}

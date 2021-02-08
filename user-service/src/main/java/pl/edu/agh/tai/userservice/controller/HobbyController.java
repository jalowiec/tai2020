package pl.edu.agh.tai.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.userservice.domain.*;
import pl.edu.agh.tai.userservice.repository.HobbyRepository;
import pl.edu.agh.tai.userservice.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class HobbyController {

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/hobby")
    public void addHobby(@RequestBody HobbyRequestBody hobbyRequestBody,@AuthenticationPrincipal Jwt jwt) throws Exception {
        User user = userRepository.findById(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
        Hobby hobby = new Hobby();
        if(user != null){
            System.out.println(hobbyRequestBody.getDescription());
            System.out.println(hobbyRequestBody.getName());

            hobby.setDescription(hobbyRequestBody.getDescription());
            hobby.setName(hobbyRequestBody.getName());
            hobby.setUser(user);
        } else {
            throw new Exception("Nie znaleziono w bazie uzytkownika");
        }

        hobbyRepository.save(hobby);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/hobby/{hobbyId}")
    public void deleteHobby(@PathVariable Long hobbyId, @AuthenticationPrincipal Jwt jwt) throws Exception {
        Optional<Hobby> hobby = hobbyRepository.findById(hobbyId);
        if(hobby.isPresent()){
            if(hobby.get().getUser().getUser_id()== getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString())){
                hobbyRepository.deleteById(hobbyId);
            } else {
                throw new Exception("Nie znaleziono hobby dla uzytkownika");
            }
        } else {
            throw new Exception("Nie znaleziono hobby dla uzytkownika");
        }
    }



    @RequestMapping(method = RequestMethod.GET, value = "/hobby", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Hobby> getHobbies(@AuthenticationPrincipal Jwt jwt){

        return hobbyRepository.findByHobbyOwner(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
    }






    private int getUserIdFromKeycloakUser(String keyCloakUser){
        Map<String, Integer> map = new HashMap<>();
        map.put("tai1", 1);
        map.put("tai2", 2);
        map.put("tai3", 3);
        return map.get(keyCloakUser);
    }



}
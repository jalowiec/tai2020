package pl.edu.agh.tai.userservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.tai.userservice.resources.Hobbies;
import pl.edu.agh.tai.userservice.service.ClientService;
import pl.edu.agh.tai.userservice.resources.ClientProfile;
import pl.edu.agh.tai.userservice.resources.Coordinates;


@RestController
@RequestMapping("/user-manager")
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    public ClientController(){

        clientService = new ClientService();
    }

    @RequestMapping(value = "/users/{value:.+}/showProfile")
    public ClientProfile showProfile(@PathVariable(value = "value") int clientID){
        return clientService.showProfile(clientID);
    }

    @RequestMapping(value = "/users/{value:.+}/coordinates/coordinate")
    public Coordinates getCoordinates(@PathVariable(value = "value") int clientID){
        return clientService.getCoordinates(clientID);
    }

    @RequestMapping(value = "/users/{value:.+}/hobbies")
    public Hobbies getHobbies(@PathVariable(value = "value") int clientID){
        return clientService.getHobbies(clientID);
    }
}
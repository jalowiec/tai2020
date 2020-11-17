package pl.edu.agh.tai.userservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.tai.userservice.service.ClientService;
import pl.edu.agh.tai.userservice.resources.ClientProfile;


@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    public ClientController(){

        clientService = new ClientService();
    }


    @RequestMapping(value = "/showProfile/{value:.+}")
    public ClientProfile showProfile(@PathVariable(value = "value") int clientID){
        return clientService.showProfile(clientID);
    }
}
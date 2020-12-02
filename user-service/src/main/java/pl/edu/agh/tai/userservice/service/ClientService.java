package pl.edu.agh.tai.userservice.service;

import pl.edu.agh.tai.userservice.repository.ClientRepository;
import pl.edu.agh.tai.userservice.resources.ClientProfile;
import pl.edu.agh.tai.userservice.resources.Coordinates;
import pl.edu.agh.tai.userservice.resources.Hobbies;

public class ClientService {

    private final ClientRepository clientRepository;

    /*public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }*/

    public ClientService() {
        clientRepository = new ClientRepository();
    }

    public ClientProfile showProfile(int ClientId){
        return clientRepository.showProfile(ClientId);
    }

    public Coordinates getCoordinates(int ClientID) { return clientRepository.getCoordinates(ClientID); }

    public Hobbies getHobbies(int clientID) { return clientRepository.getHobbies(clientID); }

}
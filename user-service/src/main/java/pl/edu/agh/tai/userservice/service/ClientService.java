package pl.edu.agh.tai.userservice.service;

import pl.edu.agh.tai.userservice.repository.ClientRepository;
import pl.edu.agh.tai.userservice.resources.ClientProfile;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientService() {
        clientRepository = new ClientRepository();
    }

    public ClientProfile showProfile(int ClientId){
        return clientRepository.showProfile(ClientId);
    }
}
package pl.edu.agh.tai.userservice.repository;

import pl.edu.agh.tai.userservice.resources.ClientProfile;

public class ClientRepository {

    public ClientProfile showProfile(int clientId){

        return new ClientProfile(clientId, "Jan", "Kowalski", "jan.kowalski@agh.edu.pl");
    }
}

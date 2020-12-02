package pl.edu.agh.tai.userservice.repository;

import pl.edu.agh.tai.userservice.resources.ClientProfile;
import pl.edu.agh.tai.userservice.resources.Coordinates;
import pl.edu.agh.tai.userservice.resources.Hobbies;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    public ClientProfile showProfile(int clientId){

        return new ClientProfile(clientId, "Jan", "Kowalski", "jan.kowalski@agh.edu.pl");
    }

    public Coordinates getCoordinates(int clientId){

        return new Coordinates(50.064651, 19.944981);

    }

    public Hobbies getHobbies(int clientID) {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Playing instruments");
        hobbies.add("Plants carrying");

        return new Hobbies(hobbies);
    }
}

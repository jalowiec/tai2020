package pl.edu.agh.tai.userservice.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientProfile {

    private final int clientId;
    private final String firstName;
    private final String surname;
    private final String email;

    public ClientProfile(
            @JsonProperty("clientId") int clientId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("surname") String surname,
            @JsonProperty("email") String email) {

        this.clientId = clientId;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    public int getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }



}

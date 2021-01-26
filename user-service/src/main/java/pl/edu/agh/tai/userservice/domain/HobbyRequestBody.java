package pl.edu.agh.tai.userservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class HobbyRequestBody {
    private String Name;
    private String Description;

    public HobbyRequestBody(@JsonProperty("name") String name, @JsonProperty("description")  String description) {
        Name = name;
        Description = description;
    }

    public HobbyRequestBody() {
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
}

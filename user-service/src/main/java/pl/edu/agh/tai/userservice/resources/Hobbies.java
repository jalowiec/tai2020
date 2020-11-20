package pl.edu.agh.tai.userservice.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Hobbies {

    private final List<String> hobbies;

    public Hobbies(
            @JsonProperty("hobbies") List<String> hobbies) {

        this.hobbies = hobbies;


    }

    public List<String> getHobbies() {
        return hobbies;
    }



}

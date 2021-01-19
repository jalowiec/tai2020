package pl.edu.agh.tai.userservice.service;

import org.springframework.stereotype.Component;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.UserRepository;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(){
        this.userRepository = new UserRepository();
    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getProfile(int userID){
        return userRepository.getProfile(userID);
    }



    public List<Hobby> getHobbies(int userID) {
        return userRepository.getHobbies(userID);
    }

    public void addProfile(User user) {
        userRepository.addProfile(user);
        }

    public void updateProfile(User user, int userID) {
        userRepository.updateProfile(user, userID);
    }

    public void deleteProfile(int userID) {
        userRepository.deleteProfile(userID);
    }

    public Coordinates getCoordinates(int userID) {
        return userRepository.getCoordinates(userID);
    }

    public void addCoordinate(Coordinates coordinates, int userID) {

        userRepository.addCoordinates(coordinates, userID);
    }

    public void updateCoordinates(Coordinates coordinates, int userID) {
        userRepository.updateCoordinates(coordinates, userID);
    }

    public void deleteCoordinates(int userID) {
        userRepository.deleteCoordinates(userID);
    }
}
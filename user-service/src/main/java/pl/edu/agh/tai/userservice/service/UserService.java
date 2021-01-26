package pl.edu.agh.tai.userservice.service;

import org.springframework.stereotype.Component;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.UserSessionRepository;

import java.util.List;

@Component
public class UserService {

    private final UserSessionRepository userSessionRepository;

    public UserService(){
        this.userSessionRepository = new UserSessionRepository();
    }
    public UserService(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    public User getProfile(int userID){
        return userSessionRepository.getProfile(userID);
    }



    public List<Hobby> getHobbies(int userID) {
        return userSessionRepository.getHobbies(userID);
    }

    public void addProfile(User user) {
        userSessionRepository.addProfile(user);
        }

    public void updateProfile(User user, int userID) {
        userSessionRepository.updateProfile(user, userID);
    }

    public void deleteProfile(int userID) {
        userSessionRepository.deleteProfile(userID);
    }

    public Coordinates getCoordinates(int userID) {
        return userSessionRepository.getCoordinates(userID);
    }

    public void updateCoordinates(Coordinates coordinates, int userID) {
        userSessionRepository.updateCoordinates(coordinates, userID);
    }

    public void deleteCoordinates(int userID) {
        userSessionRepository.deleteCoordinates(userID);
    }
}
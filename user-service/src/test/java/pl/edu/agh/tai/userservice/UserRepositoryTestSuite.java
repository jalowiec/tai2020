package pl.edu.agh.tai.userservice;

import org.junit.jupiter.api.Test;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.UserSessionRepository;

public class UserRepositoryTestSuite {

    UserSessionRepository userSessionRepository = new UserSessionRepository();

    @Test
    public void getProfileTest(){
        User user = userSessionRepository.getProfile(1);
        System.out.println(user.toString());

    }

    @Test
    public void getCoordinates(){
        Coordinates coordinates = userSessionRepository.getCoordinates(1);
        System.out.println(coordinates.toString());
    }

}
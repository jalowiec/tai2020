package pl.edu.agh.tai.userservice;

import org.junit.jupiter.api.Test;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.UserRepository;

public class UserRepositoryTestSuite {

    UserRepository userRepository = new UserRepository();

    @Test
    public void getProfileTest(){
        User user = userRepository.getProfile(1);
        System.out.println(user.toString());

    }

    @Test
    public void getCoordinates(){
        Coordinates coordinates = userRepository.getCoordinates(1);
        System.out.println(coordinates.toString());
    }

}
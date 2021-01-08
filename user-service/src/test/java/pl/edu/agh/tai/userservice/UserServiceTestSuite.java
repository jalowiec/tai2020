package pl.edu.agh.tai.userservice;

import org.junit.Test;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.repository.UserRepository;
import pl.edu.agh.tai.userservice.service.UserService;

public class UserServiceTestSuite {

    UserService userService = new UserService();

    @Test
    public void getProfileTest(){
        User user = userService.getProfile(1);
        System.out.println(user.toString());

    }
}

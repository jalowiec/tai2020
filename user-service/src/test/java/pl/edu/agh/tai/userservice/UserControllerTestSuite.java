package pl.edu.agh.tai.userservice;

import org.junit.Test;
import pl.edu.agh.tai.userservice.controller.UserController;
import pl.edu.agh.tai.userservice.domain.User;

public class UserControllerTestSuite {

    UserController userController = new UserController();

    @Test
    public void getProfileTest(){
        User user = userController.getProfile(1);
        System.out.println(user.toString());

    }
}

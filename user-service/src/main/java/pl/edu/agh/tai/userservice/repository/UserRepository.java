package pl.edu.agh.tai.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(Integer userId);
    List<User> findAll();
}


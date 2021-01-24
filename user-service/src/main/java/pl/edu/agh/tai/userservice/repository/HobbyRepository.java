package pl.edu.agh.tai.userservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.domain.Hobby;

import java.util.List;
@Repository
public interface HobbyRepository extends CrudRepository<Hobby, Long> {

    Hobby findById(int hobbyIdd);

    @Query(value = "SELECT * FROM HOBBIES WHERE USERID = ?1", nativeQuery = true)
    List<Hobby> findByHobbyOwner(int userId);
}


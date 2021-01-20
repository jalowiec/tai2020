package pl.edu.agh.tai.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.userservice.domain.Coordinates;

public interface CoordinatesRepository extends CrudRepository<Coordinates, Long> {

    Coordinates findByUserID(Integer userId);
}


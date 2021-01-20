package pl.edu.agh.tai.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.tai.userservice.domain.Coordinates;
import pl.edu.agh.tai.userservice.repository.CoordinatesRepository;

@Service
public class CoordinatesService {


    @Autowired
    private CoordinatesRepository coordinatesRepository;


    public Coordinates getCoordinates(int userID) {
        return coordinatesRepository.findByUserID(userID);
    }
}

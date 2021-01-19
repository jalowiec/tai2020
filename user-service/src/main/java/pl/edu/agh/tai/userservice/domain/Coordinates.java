package pl.edu.agh.tai.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "coordinates")
public class Coordinates {

    @Id
    @Column(name = "UserID")
    private int UserID;

    @Column(name = "Latitude")
    private double Latitude;

    @Column(name = "Longitude")
    private double Longitude;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "UserID")
    private User user;

    public Coordinates() {
    }

    public Coordinates(/*@JsonProperty("UserID") int UserID,*/
                       @JsonProperty("Latitude") double Latitude,
                       @JsonProperty("Longitude") double Longitude) {

        this.UserID = UserID;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserID : " + getUserID() +
                "\nLatitude: " + getLatitude() +
                "\nLongitude: " + getLongitude();
    }


}

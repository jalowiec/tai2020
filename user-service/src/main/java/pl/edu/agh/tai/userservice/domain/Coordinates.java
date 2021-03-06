package pl.edu.agh.tai.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "coordinates")
public class Coordinates {

    @Id
    @Column(name = "UserID")
    private int userID;

    @Column(name = "Latitude")
    private double Latitude;

    @Column(name = "Longitude")
    private double Longitude;

  /*  @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "UserID")
    private User user;
*/
    public Coordinates() {
    }

    public Coordinates(int userID,
                       @JsonProperty("Latitude") double Latitude,
                       @JsonProperty("Longitude") double Longitude) {

        this.userID = userID;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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



    @Override
    public String toString() {
        return "UserID : " + getUserID() +
                "\nLatitude: " + getLatitude() +
                "\nLongitude: " + getLongitude();
    }


}

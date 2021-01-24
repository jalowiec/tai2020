package pl.edu.agh.tai.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hobbies")
public class Hobby implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HobbyID", nullable = false)
    private int id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "Name", columnDefinition = "varchar(30)")
    private String Name;

    @Column(name = "Description", columnDefinition = "varchar(30)")
    private String Description;

    public Hobby() {
    }

    public Hobby(User user, String Name, String Description) {

        this.user = user;
        this.Name = Name;
        this.Description = Description;
    }

    public int getHobby_id() {
        return id;
    }

    public void setHobby_id(int HobbyID) {
        this.id = HobbyID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = Description;
    }
}
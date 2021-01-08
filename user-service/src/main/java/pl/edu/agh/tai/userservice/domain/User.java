package pl.edu.agh.tai.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user", schema = "userservice")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", nullable = false)
    private int UserID;

    @Column(name = "FirstName", nullable = false, columnDefinition = "varchar(30)")
    private String FirstName;

    @Column(name = "Surname", nullable = false, columnDefinition = "varchar(30)")
    private String Surname;

    @Column(name = "Email", columnDefinition = "varchar(30)")
    private String Email;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Hobby> hobbies;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "userfeature",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "FeatureID")
    )
    private Set<Feature> userfeature;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Coordinates coordinates;

    public User() {
    }

    public User(@JsonProperty("first_name") String first_name,
                @JsonProperty("surname") String surname,
                @JsonProperty("email") String email) {
        this.FirstName = first_name;
        this.Surname = surname;
        this.Email = email;
    }

    public int getUser_id() {
        return UserID;
    }

    public void setUser_id(int user_id) {
        this.UserID = UserID;
    }

    public String getFirst_name() {
        return FirstName;
    }

    public void setFirst_name(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = Email;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "UserID : " + getUser_id() +
                "\nFirstName: " + getFirst_name() +
                "\nSurname: " + getSurname() +
                "\nEmail: " + getEmail();
    }

}
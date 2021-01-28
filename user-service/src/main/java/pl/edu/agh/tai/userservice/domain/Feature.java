package pl.edu.agh.tai.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "feature", schema = "userservice")
public class Feature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeatureID", nullable = false)
    private int FeatureID;

    @Column(name = "Name", columnDefinition = "varchar(30)")
    private String Name;

    @Column(name = "Description", columnDefinition = "varchar(30)")
    private String Description;

    @JsonIgnore
    @ManyToMany(mappedBy = "userfeature", cascade = CascadeType.ALL)
    private Set<User> userfeature;

    public Feature() {
    }

    public Feature(String name, String description) {
        Name = name;
        Description = description;
    }

    public int getFeatureID() {
        return FeatureID;
    }

    public void setFeatureID(int featureID) {
        FeatureID = featureID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Set<User> getUserfeature() {
        return userfeature;
    }

    public void setUserfeature(Set<User> userfeature) {
        this.userfeature = userfeature;
    }
}
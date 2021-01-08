CREATE DATABASE userservice;
USE userservice;
CREATE TABLE User (
	UserID int NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(30),
    Surname VARCHAR(30),
    Email VARCHAR(30),
    PRIMARY KEY (UserID)
);
CREATE TABLE Feature (
	FeatureID int NOT NULL AUTO_INCREMENT,
    Name VARCHAR(30),
    Description VARCHAR(30),
    PRIMARY KEY (FeatureID)
);
CREATE TABLE UserFeature (
	FeatureID int,
    UserID int,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (FeatureID) REFERENCES Feature(FeatureID)
);
CREATE TABLE Hobbies (
    HobbyID int NOT NULL AUTO_INCREMENT,
    UserID int,
    Name VARCHAR(30),
    Description VARCHAR(30),
    PRIMARY KEY (HobbyID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);
CREATE TABLE Coordinates (
    UserID int,
    Latitude double,
    Longitude double,
    PRIMARY KEY (UserID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
)
CREATE DATABASE userservice_test;
USE userservice_test;
CREATE TABLE User (
	UserID int NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(20),
    Surname VARCHAR(30),
    Email VARCHAR(20),
    PRIMARY KEY (UserID)
);
CREATE TABLE Feature (
	FeatureID int NOT NULL AUTO_INCREMENT,
    Name VARCHAR(20),
    Description VARCHAR(30),
    PRIMARY KEY (FeatureID)
);
CREATE TABLE UserFeature (
	FeatureID int,
    UserID int,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (FeatureID) REFERENCES Feature(FeatureID)
);
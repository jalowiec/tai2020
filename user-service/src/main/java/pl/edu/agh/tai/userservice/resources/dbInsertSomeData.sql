INSERT INTO userservice.user (FirstName, Surname, Email) VALUES ('Kamil','Krakowski','kamil.krakowski@fakemail.com');
INSERT INTO userservice.user (FirstName, Surname, Email) VALUES ('Lukasz', 'Jalowiecki', 'lukasz.jalowiecki@fakemail.com');
INSERT INTO userservice.hobbies (UserID, Name, Description) VALUES (1, 'Playing instruments', 'Guitar playing, piano playing');
INSERT INTO userservice.hobbies (UserID, Name, Description) VALUES (1, 'Reading', 'Fantasy');
INSERT INTO userservice.hobbies (UserID, Name, Description) VALUES (2, 'Programming', 'Java, Python');
INSERT INTO userservice.hobbies (UserID, Name, Description) VALUES (2, 'Hiking', 'Poland & abroad');
INSERT INTO userservice.feature(Name, Description) VALUES ('air-service', 'some description');
INSERT INTO userservice.feature(Name, Description) VALUES ('image-service', 'some description');
INSERT INTO userservice.feature(Name, Description) VALUES ('quotes-service', 'some description');
INSERT INTO userservice.feature(Name, Description) VALUES ('task-service', 'some description');
INSERT INTO userservice.userfeature(FeatureID, UserID)VALUES (1,2);
INSERT INTO userservice.userfeature(FeatureID, UserID) VALUES (2,1);
INSERT INTO userservice.userfeature(FeatureID, UserID) VALUES (2,2);
INSERT INTO userservice.userfeature(FeatureID, UserID) VALUES (3,1);
INSERT INTO userservice.userfeature(FeatureID, UserID) VALUES (4,2);
INSERT INTO userservice.coordinates(UserID, Latitude, Longitude) VALUES (1, 50.064651, 19.944981)
INSERT INTO userservice.coordinates(UserID, Latitude, Longitude) VALUES (2, 50.280529, 19.562180)
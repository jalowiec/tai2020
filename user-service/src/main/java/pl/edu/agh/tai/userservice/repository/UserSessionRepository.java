package pl.edu.agh.tai.userservice.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import pl.edu.agh.tai.userservice.domain.Hobby;
import pl.edu.agh.tai.userservice.domain.User;
import pl.edu.agh.tai.userservice.domain.Coordinates;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;


@Repository
public class UserSessionRepository {

    private static SessionFactory sessionFactory = null;
    private static Session session;



    public User getProfile(int userID){

        User user = null;
        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createNativeQuery("SELECT UserID, FirstName, Surname, Email FROM user where UserID = ?", User.class).setParameter(1, userID);
            user = (User)q.getSingleResult();

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");

        }
        return user;
    }



    public List<Hobby> getHobbies(int userID) {
        List<Hobby> hobbies = null;

        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createNativeQuery("SELECT HobbyID, UserID, Name, Description FROM hobbies where UserID = ?", Hobby.class).setParameter(1, userID);
            hobbies =  q.getResultList();

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");

        }
        return hobbies;
    }


    public void addProfile(User user) {
        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(user);

            tx.commit();

        } catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");
        }
    }

    public void updateProfile(User user, int userID) {

        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            User userToChange = session.get(User.class, userID);

            userToChange.setFirst_name(user.getFirst_name());
            userToChange.setSurname(user.getSurname());
            userToChange.setEmail(user.getEmail());

            tx.commit();

        } catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");

        }
    }

    public void deleteProfile(int userID) {
        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.delete(session.get(User.class, userID));

            tx.commit();

        } catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");

        }
    }

    public Coordinates getCoordinates(int userID){

        Coordinates coordinates = null;

        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createNativeQuery("SELECT UserID, Latitude, Longitude FROM coordinates where UserID = ?", Coordinates.class).setParameter(1, userID);
            coordinates = (Coordinates) q.getResultList();

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");

        }
        return coordinates;
    }



    public void updateCoordinates(Coordinates coordinates, int userID){
        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            Coordinates coordinatesToChange = session.get(Coordinates.class, userID);

            coordinatesToChange.setLatitude(coordinates.getLatitude());
            coordinatesToChange.setLongitude(coordinates.getLongitude());


            tx.commit();

        } catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");

        }
    }

    private static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }


    public void deleteCoordinates(int userID) {
        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.delete(session.get(Coordinates.class, userID));

            tx.commit();

        } catch (Exception e){
            e.printStackTrace();

            System.err.println("------------------------Exception caught------------------");

        }
    }
}

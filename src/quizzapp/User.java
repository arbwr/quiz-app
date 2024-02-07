package quizzapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract public class User implements Serializable {

    private static int idCounter = 0;
    private static final String PATH = "users.dat";

    private String email;
    private String password;
    private String role;
    private String fullName;
    private int ID;

    public User(String email, String password, String role, String fullName) {
        this.ID = ++idCounter;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /* User files */
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            /* Ignore if the file doesn't exist initially */
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void createUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User authenticate(String email, String password) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                /* Auth successful */
                return user;
            }
        }
        return null;
    }

    /* Abstract methods */
    public abstract void displayDashboard();
}

package quizzapp;

public class Professor extends User {

    public Professor(String email, String password, String fullName) {
        super(email, password, "Professor", fullName);
    }

    @Override
    public void displayDashboard() {
        // Implement professor dashboard display
        System.out.println("Professor Dashboard");
    }
}
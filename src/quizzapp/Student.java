package quizzapp;

public class Student extends User {

    public Student(String email, String password, String fullName) {
        super(email, password, "Student", fullName);
    }

    @Override
    public void displayDashboard() {
        System.out.println("Student Dashboard");
    }
}

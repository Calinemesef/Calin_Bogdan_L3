package lab3.classes;

import java.util.List;

public class Student extends Person{

    private long studentId;
    private int totalCredits;

    private List<Long> enrolledCourses;

    public Student(String firstName, String lastName, long IDstudent){

        super(firstName, lastName);
        this.studentId = IDstudent;
    }

    public Student(String firstName, String lastName, long studentId, int totalCredits, List<Long> enrolledCourses) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
    }

    public long getStudentId() {
        return studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Long> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Long> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    @Override
    public String toString() {
        return "Studentul " + getFirstName() + " " + getLastName() + " cu Id-ul: " + getStudentId() + "\n";
    }
}

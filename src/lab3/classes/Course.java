package lab3.classes;

import java.util.List;

public class Course {

    private String name;
    private Person teacher;
    public int maxEnrollment;
    private List<Student> stundentsEnrolled;
    public int credits;
    private int courseId;


    public Course(String name, Person teacher, int maxEnrollment, List<Student> stundentsEnrolled, int credits, int courseId) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.stundentsEnrolled = stundentsEnrolled;
        this.credits = credits;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Student> getStundentsEnrolled() {
        return stundentsEnrolled;
    }

    public void setStundentsEnrolled(List<Student> stundentsEnrolled) {
        this.stundentsEnrolled = stundentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}

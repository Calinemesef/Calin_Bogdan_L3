package lab3.classes;

import java.util.List;

public class Teacher extends Person{

    private int teacherId;
    private List<Course> courses;

    public Teacher(String firstName, String lastName, int teacherId, List<Course> courses) {
        super(firstName, lastName);
        this.teacherId = teacherId;
        this.courses = courses;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

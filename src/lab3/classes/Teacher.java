package lab3.classes;

import java.util.List;

public class Teacher extends Person{

    private long teacherId;
    private List<Course> courses;

    public Teacher(String firstName, String lastName, long teacherId, List<Course> courses) {
        super(firstName, lastName);
        this.teacherId = teacherId;
        this.courses = courses;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

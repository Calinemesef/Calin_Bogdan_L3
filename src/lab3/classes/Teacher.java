package lab3.classes;

import java.util.List;

public class Teacher extends Person{

    private long teacherId;
    private List<Long> courses;

    public Teacher(String firstName, String lastName){
        super(firstName, lastName);
    }

    public Teacher(String firstName, String lastName, long teacherId, List<Long> courses) {
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

    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }

    public String toString(){
        return "Profesorul " + getFirstName() + " " + getLastName() + " cu id-ul " + teacherId +  "\n" ;
    }
}

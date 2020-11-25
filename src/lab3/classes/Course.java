package lab3.classes;

import java.util.List;

public class Course implements  Comparable<Course>{

    private String nume;
    private Long teacherId;
    public int maxEnrollment;
    private List<Long> studentsEnrolled;
    public int credits;
    private long courseId;

    public Course(String name){

    }

    public Course(String nume, Long teacherId, int maxEnrollment, List<Long> studentsEnrolled, int credits, long courseId) {
        this.nume = nume;
        this.teacherId = teacherId;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
        this.courseId = courseId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Long getTeacher() {
        return teacherId;
    }

    public void setTeacher(Long teacherId) {
        this.teacherId = teacherId;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Long> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Long> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String toString(){
        return "Cursul " + nume + " cu id-ul " + courseId + " si cu numarul de credite " + credits + "\n";
    }


    @Override
    public int compareTo(Course o) {
        return this.getNume().compareTo(o.getNume());
    }
}

package lab3.classes;

import java.util.List;

public class Course implements  Comparable<Course>{

    private String name;
    private Long teacherId;
    public int maxEnrollment;
    private List<Long> studentsEnrolled;
    public int credits;
    private long courseId;

    public Course(String name){

    }

    public Course(String name, Long teacherId, int maxEnrollment, List<Long> studentsEnrolled, int credits, long courseId) {
        this.name = name;
        this.teacherId = teacherId;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Cursul " + name + " cu id-ul " + courseId + " si cu numarul de credite " + credits + "\n";
    }


    @Override
    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }
}

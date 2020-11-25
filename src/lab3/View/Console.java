package lab3.View;
import lab3.classes.Course;
import lab3.classes.Student;
import lab3.classes.Teacher;
import lab3.controller.*;
import lab3.system.*;

import java.util.List;
import java.util.Scanner;
/*
error handling
diagrama
studentctrl - set id set lista set credits
curatat cod
comentarii
override in fisier json

 */

public class Console {

    protected CourseController cursCtrl;
    protected StudController studCtrl;
    protected TeacherController teacherCtrl;
    protected RegistrationSystem registrationSystem;
    public int input = 1;


    public Console(CourseController cursCtrl, StudController studCtrl, TeacherController teacherCtrl) {
        this.cursCtrl = cursCtrl;
        this.studCtrl = studCtrl;
        this.teacherCtrl = teacherCtrl;
        this.registrationSystem = new RegistrationSystem(teacherCtrl.repo, studCtrl.repo, cursCtrl.repo);
    }

    public void display() {
        System.out.println("MENU");
        System.out.println("0. Quit");
        System.out.println("1. Student Controller");
        System.out.println("2. Course Controller");
        System.out.println("3. Teacher Controller");
        System.out.println("4. Registration System");
        Scanner in = new Scanner(System.in);
        input = in.nextInt();
        while (input != 0) {

            if (input == 4) {
                int x;

                System.out.println("Registration System");
                System.out.println("0. Quit");
                System.out.println("1. Register Student to a Course");
                System.out.println("2. Retrieve courses with free places");
                System.out.println("3. Retrieve students enrolled for a course");
                System.out.println("4. Print all courses");
                x = in.nextInt();
                if (x == 0) {
                    break;
                } else if (x == 1) {
                    System.out.println("1. Register Student to a Course");

                    System.out.println("Student ID:");
                    long idStudent = in.nextLong();
                    System.out.println("Course ID:");
                    long idCourse = in.nextLong();
                    Course curs = cursCtrl.findOne(idCourse);
                    Student student = studCtrl.findOne(idStudent);
                    registrationSystem.register(curs, student);
                }
                if (x == 2) {
                    System.out.println("2. Retrieve courses with free places");

                    List<Course> afiseazaCursuri = registrationSystem.retrieveCoursesWithFreePlaces();
                    for (Course element : afiseazaCursuri) {
                        System.out.println(element);
                    }
                }
                if (x == 3) {
                    System.out.println("3. Retrieve students enrolled for a course");

                    System.out.println("Course ID: ");
                    long idCourse = in.nextLong();
                    Course curs = cursCtrl.findOne(idCourse);
                    List<Long> listaIduri = registrationSystem.retrieveStudentsEnrolledForACourse(curs);
                    for (Long id : listaIduri)
                        System.out.println(id);
                }
                if (x == 4) {
                    System.out.println("4. Print all courses");

                    List<Course> toateCursurile = registrationSystem.getAllCourses();
                    for (Course c : toateCursurile)
                        System.out.println(c);
                }
            } else if (input == 3) {

                int x;
                System.out.println("Teacher Controller");
                System.out.println("0. Exit");
                System.out.println("1. Delete course from teacher");
                System.out.println("2. Show all teachers");

                x = in.nextInt();
                if (x == 0) {
                    break;
                }
                if (x == 1) {
                    System.out.println("1. Delete course from teacher");
                    System.out.println("Teacher ID:");
                    Long teacherID = in.nextLong();
                    System.out.println("Course ID:");
                    Long courseID = in.nextLong();

                    Teacher teacher = teacherCtrl.findOne(teacherID);
                    Course course = cursCtrl.findOne(courseID);

                    boolean rezultat = teacherCtrl.teacherDeleteCourse(teacher, course, cursCtrl.repo);
                    System.out.println(rezultat);
                } else if (x == 2) {
                    System.out.println("2. Show all teachers");
                    Iterable<Teacher> teachers = teacherCtrl.findAll();
                    for (Teacher t : teachers) {
                        System.out.println(t);
                    }
                }
            } else if (input == 2) {
                System.out.println("Course Controller");
                System.out.println("0. Exit");
                System.out.println("1. Sort by name");
                System.out.println("2. Filter by credits");
                System.out.println("3. Show all courses");

                int x = in.nextInt();
                if (x == 0) {
                    break;
                }
                else if (x == 1) {
                    System.out.println("1. Sort by name");
                    cursCtrl.sortByName();
                    for (Course c : cursCtrl.repo.courses) {
                        System.out.println(c);
                    }
                }
                else if (x == 2) {
                    System.out.println("2. Filter by credits");
                    System.out.println("Courses with credits < 8 only:");
                    cursCtrl.filterCredits();
                    for (Course c : cursCtrl.repo.courses) {
                        System.out.println(c);
                    }
                }
                else if (x == 3){
                    System.out.println("3. Show all courses");
                    for(Course c:cursCtrl.repo.courses)
                        System.out.println(c);
                }
            } else if (input == 1){
                System.out.println("Student Controller");
                System.out.println("0. Exit");
                System.out.println("1. Show all students");
                System.out.println("2. Add student");
                System.out.println("3. Delete student");
                int x = in.nextInt();

                if (x == 0){
                    break;
                }
                else if (x == 1){
                    System.out.println("1. Show all students");
                    for(Student s:studCtrl.repo.students){
                        System.out.println(s);
                    }
                }
                else if (x == 2){
                    System.out.println("2. Add student");
                    System.out.println("first Name: ");
                    String firstName = in.next();
                    System.out.println("last Name: ");
                    String lastName = in.next();
                    Student s = new Student(firstName,lastName);
                    studCtrl.save(s);
                    System.out.println("Student added");
                }
                else if (x == 3){
                    System.out.println("3.Delete student");
                    System.out.println("Student ID: ");
                    long id = in.nextLong();
                    Student s= studCtrl.delete(id);
                    if(s != null){
                        System.out.println("Student deleted");
                    }else
                        System.out.println("Student not found");
                }
            }
            System.out.println("MENU");
            System.out.println("0. Quit");
            System.out.println("1. Student Controller");
            System.out.println("2. Course Controller");
            System.out.println("3. Teacher Controller");
            System.out.println("4. Registration System");
            input = in.nextInt();
        }
    }
}

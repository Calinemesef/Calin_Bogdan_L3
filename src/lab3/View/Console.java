package lab3.View;
import lab3.classes.Course;
import lab3.classes.Student;
import lab3.controller.*;
import lab3.system.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {

    protected CourseController cursCtrl;
    protected StudController studCtrl;
    protected TeacherController teacherCtrl;
    protected RegistrationSystem registrationSystem;
    public int input =1;


    public Console(CourseController cursCtrl, StudController studCtrl, TeacherController teacherCtrl) {
        this.cursCtrl = cursCtrl;
        this.studCtrl = studCtrl;
        this.teacherCtrl = teacherCtrl;
        this.registrationSystem = new RegistrationSystem(teacherCtrl.repo,studCtrl.repo,cursCtrl.repo);
    }

    public Console() {

    }

    public void display(){
        while (input != 0) {
            System.out.println("Meniu Complet");
            System.out.println("0. Quit");
            System.out.println("1. Controller Studenti");
            System.out.println("2. Controller Cursuri");
            System.out.println("3. Controller Profesori");
            System.out.println("4. Registration System");

            Scanner in = new Scanner(System.in);
            input = in.nextInt();
            if( input == 4)
            {
                int x;

                System.out.println("Meniu Registration System");
                System.out.println("0. Quit");
                System.out.println("1. Register Student to a Course");
                System.out.println("2. Retrieve courses with free places");
                System.out.println("3. Retrieve students enrolled for a course");
                System.out.println("4. Print all courses");
                x = in.nextInt();
                if(x == 0){
                    break;
                }
                else
                    if(x == 1){
                        System.out.println("1. Register Student to a Course");

                        System.out.println("Student ID:");
                        long idStudent = in.nextLong();
                        System.out.println("Course ID:");
                        long idCourse = in.nextLong();
                        Course curs = cursCtrl.repo.findOne(idCourse);
                        Student student = studCtrl.repo.findOne(idStudent);
                        registrationSystem.register(curs,student);
                    }
                    if(x == 2){
                        System.out.println("2. Retrieve courses with free places");

                        List<Course> afiseazaCursuri = registrationSystem.retrieveCoursesWithFreePlaces();
                        for(Course element: afiseazaCursuri){
                            System.out.println(element);
                        }
                    }
                    if(x == 3){
                        System.out.println("3. Retrieve students enrolled for a course");

                        System.out.println("Course ID: ");
                        long idCourse = in.nextLong();
                        Course curs = cursCtrl.repo.findOne(idCourse);
                        List<Long> listaIduri = registrationSystem.retrieveStudentsEnrolledForACourse(curs);
                        for(Long id:listaIduri)
                            System.out.println(id);
                    }
                    if(x == 4){
                        System.out.println("4. Print all courses");

                        List<Course> toateCursurile = registrationSystem.getAllCourses();
                        for(Course c:toateCursurile)
                            System.out.println(c);
                    }


            }



        }

    }

}

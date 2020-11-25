package lab3.View;
import lab3.controller.*;
import lab3.system.*;

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
                        System.out.println("Student ID:");
                        Integer idStudent = in.nextInt();
                        System.out.println("Course ID:");
                        Integer idCourse = in.nextInt();
                    }


            }



        }

    }

}

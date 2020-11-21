package lab3.controller;

import lab3.classes.Course;
import lab3.classes.Student;
import lab3.classes.Teacher;
import lab3.repository.CourseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {

    CourseController cursController = new CourseController();

    List<Student> studenti_c1 = new ArrayList<>();
    List<Student> studenti_c2 = new ArrayList<>();

    List<Course> lista_cursuri = new ArrayList<>();
    List<Course> lista_cursuri2 = new ArrayList<>();

    List<Course> enrolled1 = new ArrayList<>();
    List<Course> enrolled2 = new ArrayList<>();

    Student stud1 = new Student("PrenumeStudent","NumeStudent",155,25,enrolled1);
    Student stud2 = new Student("PrenumeStudent2","NumeStudent2",156,23,enrolled2);

    Teacher prof1 = new Teacher("Prenume","Nume",5,lista_cursuri);
    Teacher prof2 = new Teacher("Prenume2","Nume2",6,lista_cursuri2);

    Course c1 = new Course("Algebra",prof1,300, studenti_c1, 5,1);
    Course c2 = new Course("Analiza",prof2,150, studenti_c2, 4,2);
    Course c3 = new Course("Geometrie",prof2,0, studenti_c2, 4,3);
    Course c4 = new Course("OOP",prof2,0, studenti_c2, 9,4);
    Course c5 = new Course("MAP",prof2,0, studenti_c2, 6,5);
    Course c6 = new Course("Datenbanken",prof2,0, studenti_c2, 6,6);
    Course c7 = new Course("Logik",prof2,0, studenti_c2, 3,7);
    Course c8 = new Course("Sport",prof2,0, studenti_c2, 8,8);
    Course c9 = new Course("Statistica",prof2,0, studenti_c2, 5,9);
    Course c10 = new Course("Engleza",prof2,0, studenti_c2, 10,10);
    Course c11 = new Course("Engleza",prof2,0, studenti_c2, 10,111);



    @BeforeEach
    void setUp() {
        cursController.save(c1);
        cursController.save(c2);
        cursController.save(c3);
        cursController.save(c4);
        cursController.save(c5);
        cursController.save(c6);
        cursController.save(c7);
        cursController.save(c8);
        cursController.save(c9);
        cursController.save(c10);
        cursController.save(c11);

    }


    @Test
    void sortByName() {
        cursController.sortByName();
        for(int i = 0; i< CourseRepo.courses.size()-1; i++){
            //System.out.println(CourseRepo.courses.get(i));
            //assert(CourseRepo.courses.get(i).getName().compareTo(CourseRepo.courses.get(i+1).getName())==-1 || CourseRepo.courses.get(i).getName().equals(CourseRepo.courses.get(i+1).getName()));
            assert(CourseRepo.courses.get(i+1).compareTo(CourseRepo.courses.get(i))>=0);
            //System.out.println(CourseRepo.courses.get(i+1).compareTo(CourseRepo.courses.get(i)));
        }
    }

    @Test
    void filterCredits() {
        cursController.filterCredits();
        for(int i = 0; i< CourseRepo.courses.size()-1; i++){
            assert(CourseRepo.courses.get(i).getCredits()<8);
        }

    }
}
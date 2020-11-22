package lab3.controller;

import lab3.classes.Course;
import lab3.classes.Student;
import lab3.classes.Teacher;
import lab3.repository.CourseRepo;
import lab3.repository.StudRepo;
import lab3.repository.TeacherRepo;
import lab3.system.RegistrationSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TeacherControllerTest {

    TeacherController teacherController = new TeacherController();
    CourseRepo repo_curs = new CourseRepo();
    StudRepo repo_student = new StudRepo();
    TeacherRepo repo_teacher = new TeacherRepo();
    RegistrationSystem regsys = new RegistrationSystem(repo_teacher,repo_student,repo_curs);

    List<Long> studenti_c1 = new ArrayList<>();
    List<Long> studenti_c2 = new ArrayList<>();

    List<Long> lista_cursuri = new ArrayList<>();
    List<Long> lista_cursuri2 = new ArrayList<>();

    List<Long> enrolled1 = new ArrayList<>();
    List<Long> enrolled2 = new ArrayList<>();

    Student stud1 = new Student("PrenumeStudent","NumeStudent",155,25,enrolled1);
    Student stud2 = new Student("PrenumeStudent2","NumeStudent2",156,23,enrolled2);

    Teacher prof1 = new Teacher("Prenume","Nume",5,lista_cursuri);
    Teacher prof2 = new Teacher("Prenume2","Nume2",6,lista_cursuri2);

    Course c1 = new Course("Algebra",prof1.getTeacherId(),300, studenti_c1, 5,1);
    Course c2 = new Course("Analiza",prof2.getTeacherId(),150, studenti_c2, 4,2);
    Course c3 = new Course("Info",prof2.getTeacherId(),0, studenti_c2, 4,3);

    @BeforeEach
    void setUp() {
        studenti_c1.add(stud1.getStudentId());
        studenti_c2.add(stud2.getStudentId());


        lista_cursuri.add(c1.getCourseId());
        lista_cursuri.add(c2.getCourseId());
        lista_cursuri.add(c3.getCourseId());
        lista_cursuri2.add(c2.getCourseId());

        enrolled1.add(c1.getCourseId());
        enrolled2.add(c2.getCourseId());

        repo_curs.save(c1);
        repo_curs.save(c2);
        repo_curs.save(c3);

        repo_student.save(stud1);
        repo_student.save(stud2);
        repo_teacher.save(prof1);
        repo_teacher.save(prof2);
    }

    @Test
    void teacherDeleteCourse() {
        teacherController.teacherDeleteCourse(prof1,c1);
/*        for(int i = 0; i< CourseRepo.courses.size(); i++){
             System.out.println(CourseRepo.courses.get(i));
        }*/

        assert (repo_curs.findOne(c1.getCourseId())==null);
    }
}
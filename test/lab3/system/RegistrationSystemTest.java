package lab3.system;

import lab3.classes.Course;
import lab3.classes.Student;
import lab3.classes.Teacher;
import lab3.repository.CourseRepo;
import lab3.repository.StudRepo;
import lab3.repository.TeacherRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class RegistrationSystemTest {

    CourseRepo repo_curs = new CourseRepo();
    StudRepo repo_student = new StudRepo();
    TeacherRepo repo_teacher = new TeacherRepo();
    RegistrationSystem regsys = new RegistrationSystem(repo_teacher,repo_student,repo_curs);

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
    Course c3 = new Course("Info",prof2,0, studenti_c2, 4,3);


    @BeforeEach
    void setUp() {
    studenti_c1.add(stud1);
    studenti_c2.add(stud2);


    lista_cursuri.add(c1);
    lista_cursuri2.add(c2);

    enrolled1.add(c1);
    enrolled2.add(c2);

    repo_curs.save(c1);repo_curs.save(c2);repo_curs.save(c3);
    repo_student.save(stud1);repo_student.save(stud2);
    repo_teacher.save(prof1);repo_teacher.save(prof2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        assertTrue(regsys.register(c2, stud1));
        assertTrue(regsys.register(c1, stud2));
        assertFalse(regsys.register(c1,stud1));
        assertFalse(regsys.register(c2,stud2));

    }

    @Test
    void retrieveCoursesWithFreePlaces() {
        List<Course> lista = new ArrayList<>();
        List<Course> lista2 = new ArrayList<>();
        lista2.add(c1);
        lista2.add(c2);
        lista = regsys.retrieveCoursesWithFreePlaces();
        assertEquals(lista.get(1),lista2.get(1));
        assertEquals(lista.get(0),lista2.get(0));
        assertSame(lista.get(1), lista2.get(1));
        assertSame(lista.get(0), lista2.get(0));
    }

    @Test
    void retrieveStudentsEnrolledForACourse() {
        assertSame(regsys.retrieveStudentsEnrolledForACourse(c1),studenti_c1);
        assertSame(regsys.retrieveStudentsEnrolledForACourse(c2),studenti_c2);
        assertEquals(regsys.retrieveStudentsEnrolledForACourse(c2),studenti_c2);
        assertEquals(regsys.retrieveStudentsEnrolledForACourse(c1),studenti_c1);
    }

    @Test
    void getAllCourses() {
        assertEquals(regsys.getAllCourses(),repo_curs.courses);
    }
}
package lab3;

import javafx.application.Application;
import javafx.stage.Stage;
import lab3.View.GUI;
import lab3.classes.Course;
import lab3.classes.Student;
import lab3.classes.Teacher;
import lab3.fileRepository.CoursesFile;
import lab3.fileRepository.StudentsFile;
import lab3.fileRepository.TeacherFile;
import lab3.repository.CourseRepo;
import lab3.repository.StudRepo;
import lab3.repository.TeacherRepo;
import lab3.system.RegistrationSystem;
import java.io.IOException;
import java.util.List;

public class StartGUI extends Application {

    RegistrationSystem registrationSystem;
    TeacherFile fisierTeacher = new TeacherFile();
    StudentsFile fisierStudent = new StudentsFile();
    CoursesFile fisierCourse = new CoursesFile();
    TeacherRepo teacherRepo = new TeacherRepo();
    StudRepo studRepo = new StudRepo();
    CourseRepo courseRepo = new CourseRepo();
    List<Teacher> teachersF;
    List<Student> studentsF;
    List<Course> coursesF;

    /**
     * Start point of the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        teachersF =fisierTeacher.readFile();
        studentsF =fisierStudent.readFile();
        coursesF =fisierCourse.readFile();

        teacherRepo.teachers =teachersF;
        studRepo.students =studentsF;
        courseRepo.courses =coursesF;

        registrationSystem = new RegistrationSystem(teacherRepo,studRepo,courseRepo);

        GUI gui = new GUI(primaryStage, registrationSystem);
        gui.show();
    }

    @Override
    public void stop() {
        fisierTeacher.writeFile(teacherRepo.teachers);
        fisierStudent.writeFile(studRepo.students);
        fisierCourse.writeFile(courseRepo.courses);
    }
}

package lab3;

import lab3.View.Console;
import lab3.classes.Course;
import lab3.classes.Student;
import lab3.classes.Teacher;
import lab3.controller.CourseController;
import lab3.controller.StudController;
import lab3.controller.TeacherController;
import lab3.fileRepository.CoursesFile;
import lab3.fileRepository.StudentsFile;
import lab3.fileRepository.TeacherFile;
import lab3.repository.CourseRepo;
import lab3.repository.StudRepo;
import lab3.repository.TeacherRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Start point");

        TeacherFile fisierTeacher = new TeacherFile();
        StudentsFile fisierStudent = new StudentsFile();
        CoursesFile fisierCourse = new CoursesFile();
        List<Teacher> teachersF = new ArrayList<>();
        List<Student> studentsF = new ArrayList<>();
        List<Course> coursesF = new ArrayList<>();
        TeacherRepo teacherRepo = new TeacherRepo();
        StudRepo studRepo = new StudRepo();
        CourseRepo courseRepo = new CourseRepo();
        TeacherController teacherController = new TeacherController();
        StudController studController = new StudController();
        CourseController courseController = new CourseController();


        try {
            teachersF = fisierTeacher.readFile();
            studentsF = fisierStudent.readFile();
            coursesF = fisierCourse.readFile();

            teacherRepo.teachers = teachersF;
            studRepo.students = studentsF;
            courseRepo.courses = coursesF;

            teacherController.repo = teacherRepo;
            studController.repo = studRepo;
            courseController.repo = courseRepo;

            Console console = new Console(courseController, studController, teacherController);

            console.display();

            fisierTeacher.writeFile(teacherController.repo.teachers);
            fisierStudent.writeFile(studController.repo.students);
            fisierCourse.writeFile(courseController.repo.courses);

            // CtrlTeacher.repo = teacher_repo
            // Console c = new Console(CtrlTeacher,CtrlStudent,CtrlCourse)
            //System.out.println(teachers.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
//        for(int i=0;i<teacherRepo.teachers.size();i++){
//            System.out.println(teacherRepo.teachers.get(i));
//        }
//        for(int i=0;i<studRepo.students.size();i++){
//            System.out.println(studRepo.students.get(i));
//        }
//        for(int i=0;i<courseRepo.courses.size();i++){
//            System.out.println(courseRepo.courses.get(i));
//        }
//



       /* for(Teacher i : teachers){
            System.out.println(i);
        }*/

    }
}

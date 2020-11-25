package lab3;

import lab3.View.Console;
import lab3.classes.Teacher;
import lab3.fileRepository.*;

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
        Console c = new Console();
        //c.display();
        TeacherFile fisier = new TeacherFile();
        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers = fisier.readFile();
            // teacher_repo = teachers;
            // CtrlTeacher.repo = teacher_repo
            // Console c = new Console(CtrlTeacher,CtrlStudent,CtrlCourse)
            System.out.println(teachers.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Teacher i : teachers){
            System.out.println(i);
        }

    }
}

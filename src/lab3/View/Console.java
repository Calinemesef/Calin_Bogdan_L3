package lab3.View;
import lab3.controller.*;
import lab3.system.*;

public class Console {

    protected CourseController cursCtrl;
    protected StudController studCtrl;
    protected TeacherController teacherCtrl;
    protected RegistrationSystem registrationSystem;


    public Console(CourseController cursCtrl, StudController studCtrl, TeacherController teacherCtrl) {
        this.cursCtrl = cursCtrl;
        this.studCtrl = studCtrl;
        this.teacherCtrl = teacherCtrl;
        this.registrationSystem = new RegistrationSystem(teacherCtrl.repo,studCtrl.repo,cursCtrl.repo);
    }

    

}

package lab3.classes;

import lab3.classes.Course;
import lab3.classes.Student;
import lab3.repository.CourseRepo;
import lab3.repository.StudRepo;
import lab3.repository.TeacherRepo;

import java.util.List;

public class RegistrationSystem {

    TeacherRepo teacherRepository;
    StudRepo studentRepository;
    CourseRepo courseRepository;

    public RegistrationSystem(TeacherRepo teacherRepository, StudRepo studentRepository, CourseRepo courseRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    //functia care inregistreaza un nou student la un curs
    public boolean register(Course course, Student student) {
        if (course.getStudentsEnrolled().contains(student) || student.getEnrolledCourses().contains(course)) {
            return false;
        }
        if (studentRepository.findOne(student.getStudentId()) != null && courseRepository.findOne(course.getCourseId()) != null) {
            if (course.getStudentsEnrolled().size() < course.getMaxEnrollment()) {
                if (student.getTotalCredits() + course.getCredits() >= 30) {
                    System.out.println("Studentul depaseste numarul de credite maxime admise.");
                    return false;
                } else {
                    //se appenduieste cursul nou in lista de cursuri al unui student
                    List<Course> cursuriNoi;
                    cursuriNoi = student.getEnrolledCourses();
                    cursuriNoi.add(course);
                    student.setEnrolledCourses(cursuriNoi);
                    student.setTotalCredits(student.getTotalCredits() + course.getCredits());

                    //se appenduieste studentul nou inclus la lista de studenti inscrisi la un curs
                    List<Student> studentiNoi;
                    studentiNoi = course.getStudentsEnrolled();
                    studentiNoi.add(student);
                    course.setStudentsEnrolled(studentiNoi);
                    return true;
                }
            } else {
                System.out.println("Cursul ales are deja numarul maxim de studenti inscrisi.");
                return false;
            }
        } else {
            System.out.println("Nu a fost gasit studentul sau cursul ales");
            return false;
        }
    }
}
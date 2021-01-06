package lab3.system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lab3.classes.Course;
import lab3.classes.Student;
import lab3.classes.Teacher;
import lab3.repository.CourseRepo;
import lab3.repository.StudRepo;
import lab3.repository.TeacherRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

        long cursId = course.getCourseId();
        long studentId = student.getStudentId();

        if (course.getStudentsEnrolled().contains(studentId) || student.getEnrolledCourses().contains(cursId)) {
            System.out.println(" Studentul este deja inscris la curs");
            return false;
        }
        if (studentRepository.findOne(student.getStudentId()) != null && courseRepository.findOne(course.getCourseId()) != null) {
            if (course.getStudentsEnrolled().size() < course.getMaxEnrollment()) {


                if (student.getTotalCredits() + course.getCredits() > 30) {
                    System.out.println("Studentul depaseste numarul de credite maxime admise.");
                    return false;
                } else {
                    //se appenduieste cursul nou in lista de cursuri al unui student
                    List<Long> cursuriNoi;
                    cursuriNoi = student.getEnrolledCourses();
                    cursuriNoi.add(cursId);
                    student.setEnrolledCourses(cursuriNoi);
                    student.setTotalCredits(student.getTotalCredits() + course.getCredits());

                    //se appenduieste studentul nou inclus la lista de studenti inscrisi la un curs
                    List<Long> studentiNoi;
                    studentiNoi = course.getStudentsEnrolled();
                    studentiNoi.add(studentId);
                    course.setStudentsEnrolled(studentiNoi);
                    System.out.println(" Register succesful");
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

    //returneaza lista de cursuri cu locuri disponibile
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> afiseazaCursuri = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            if (course.getStudentsEnrolled().size() < course.getMaxEnrollment()) {
                afiseazaCursuri.add(course);
            }
        }
        return afiseazaCursuri;
    }


    //afiseaza studentii inscrisi la un anumit curs
    public List<Long> retrieveStudentsEnrolledForACourse(Course course) {
        if(courseRepository.findOne(course.getCourseId()) != null) {
            return course.getStudentsEnrolled();
        }
        else {
            System.out.println("Nu s-a gasit acest curs");
            return null;
        }
    }

    //returneaza o lista de cursuri
    public List<Course> getAllCourses(){
        List<Course> toateCursurile = new ArrayList<>();
        courseRepository.findAll().forEach(toateCursurile::add);
        return toateCursurile;
    }

    /**
     * Methode fur Erstellen der Observable-liste fur die Studenten
     * @param id -id von teacher
     * @return Liste von Studenten
     */
    public ObservableList<Student> observableStudents(long id) {
        Teacher teacher = teacherRepository.findOne(id);
        ObservableList<Student> students = FXCollections.observableArrayList();
        studentRepository.findAll().forEach(student -> {
            AtomicBoolean intersection = new AtomicBoolean(false);
            student.getEnrolledCourses().forEach(course -> {
                if (teacher.getCourses().contains(course))
                    intersection.set(true);
            });
            if (intersection.get())
                students.add(student);
        });
        return students;
    }

    /**
     * Methode fur Erstellen der Observable-liste fur die Kursen
     * @return Liste von Kursen
     */
    public ObservableList<Course> observableCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    /**
     * Methode fur Testen ob ein Lehrer in der liste vorkommt
     * @param teacherId
     * @param firstName
     * @param lastName
     * @return true falls ja, false falls nicht
     */
    public boolean validateTeacher(long teacherId, String firstName, String lastName) {
        Teacher teacher = this.teacherRepository.findOne(teacherId);
        if (teacher != null) {
            return teacher.getFirstName().equals(firstName) && teacher.getLastName().equals(lastName);
        }
        return false;
    }

    /**
     * Methode fur Testen ob ein Student in der Liste vorkommt
     * @param studentId
     * @param firstName
     * @param lastName
     * @return true falls ja, false falls nicht
     */
    public boolean validateStudent(long studentId, String firstName, String lastName) {
        Student student = this.studentRepository.findOne(studentId);
        if (student != null) {
            return student.getFirstName().equals(firstName) && student.getLastName().equals(lastName);
        }
        return false;
    }
}
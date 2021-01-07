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
                    studentRepository.update(student);

                    //se appenduieste studentul nou inclus la lista de studenti inscrisi la un curs
                    List<Long> studentiNoi;
                    studentiNoi = course.getStudentsEnrolled();
                    studentiNoi.add(studentId);
                    course.setStudentsEnrolled(studentiNoi);
                    courseRepository.update(course);
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
     * @param teacherID id von lehrer
     * @param firstName vorname
     * @param lastName nachname
     * @return true falls ja, false falls nicht
     */
    public boolean validateTeacher(long teacherID, String firstName, String lastName) {
        Teacher teacher = this.teacherRepository.findOne(teacherID);
        if (teacher != null) {
            return teacher.getFirstName().equals(firstName) && teacher.getLastName().equals(lastName);
        }
        return false;
    }

    /**
     * Methode fur Testen ob ein Student in der Liste vorkommt
     * @param studentID id von student
     * @param firstName vorname
     * @param lastName nachname
     * @return true falls ja, false falls nicht
     */
    public boolean validateStudent(long studentID, String firstName, String lastName) {
        Student student = this.studentRepository.findOne(studentID);
        if (student != null) {
            return student.getFirstName().equals(firstName) && student.getLastName().equals(lastName);
        }
        return false;
    }

    /**
     * Methode fur Registrieren eines Studenten zu einem Kurs, fur die GUI
     * @param studentID id des studenten
     * @param courseID id des kurses
     * @throws Exception falls notig
     */
    public void registerGUI(long studentID, long courseID) throws Exception {
        Student student = studentRepository.findOne(studentID);
        Course course = courseRepository.findOne(courseID);

        if (student != null && course != null) {
            if (student.getEnrolledCourses().contains(courseID) || course.getStudentsEnrolled().contains(studentID))
                throw new Exception("Der Student ist schon zu diesem Kurs registriert.");
            else if (course.getCredits() + student.getTotalCredits() > 30)
                throw new Exception("Kreditanzahl zu gross");
            else if (course.getMaxEnrollment() - course.getStudentsEnrolled().size() == 0)
                throw new Exception("Der Kurs hat keinen Platz");
            else {
                List<Long> courses = student.getEnrolledCourses();
                courses.add(courseID);
                student.setEnrolledCourses(courses);

                List<Long> students = course.getStudentsEnrolled();
                students.add(studentID);
                course.setStudentsEnrolled(students);

                student.setTotalCredits(student.getTotalCredits() + course.getCredits());

                this.studentRepository.update(student);
                this.courseRepository.update(course);
            }
        } else {
            throw new Exception("Student oder Kurs ungultig");
        }
    }

    public StudRepo getStudentRepo() {
        return studentRepository;
    }
}
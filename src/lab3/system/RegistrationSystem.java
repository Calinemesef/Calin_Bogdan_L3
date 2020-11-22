package lab3.system;


import lab3.classes.Course;
import lab3.classes.Student;
import lab3.repository.CourseRepo;
import lab3.repository.StudRepo;
import lab3.repository.TeacherRepo;

import java.util.ArrayList;
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

        long cursId = course.getCourseId();
        long studentId = student.getStudentId();

        if (course.getStudentsEnrolled().contains(studentId) || student.getEnrolledCourses().contains(cursId)) {
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

//    public List<Course> validateList(List<Course> cursuri) throws EmptyListException{
//
//        if(cursuri.size()==0){
//            throw new EmptyListException("Nu sunt cursuri cu locuri disponibile!");
//        }
//        else{
//            return cursuri;
//        }
//    }

}
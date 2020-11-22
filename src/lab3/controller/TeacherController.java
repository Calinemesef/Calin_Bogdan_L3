package lab3.controller;

import lab3.classes.Course;
import lab3.classes.Teacher;

import lab3.repository.CourseRepo;
import lab3.repository.ICrudRepository;
import lab3.repository.TeacherRepo;

import java.util.ArrayList;
import java.util.List;

public class TeacherController implements ICrudRepository<Teacher> {

    public TeacherRepo repo = new TeacherRepo();


    @Override
    public Teacher findOne(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Iterable<Teacher> findAll() {
        return repo.findAll();
    }

    @Override
    public Teacher save(Teacher entity) {
        return repo.save(entity);
    }

    @Override
    public Teacher delete(Long id) {
        return repo.delete(id);
    }

    @Override
    public Teacher update(Teacher entity) {
        return repo.update(entity);
    }


    /**
     *Se verifica daca exita profesorul dat in repository-ul de profesori,daca profesorul exista,
     *iar daca cursul este in lista de cursuri ale unui profesor, se creeaza o lista de cursuri noua
     *in care se adauga cursurile deja existente in lista veche atata timp cat cursul curent este
     *diferit de cursul pe care vrem sa il stergem. La sfarsit, se sterge cursul
     */
    public boolean teacherDeleteCourse(Teacher teacher, Course course) {

        if(repo.findOne(teacher.getTeacherId()) != null) {

            Long cursId = course.getCourseId();

            if(teacher.getCourses().contains(cursId)) {

                List<Long> cursuriModificateProfesori = new ArrayList<>();

                for(Long c : teacher.getCourses()) {
                    if(c != course.getCourseId()) {
                        cursuriModificateProfesori.add(c);
                    }
                }

                teacher.setCourses(cursuriModificateProfesori);

                //CourseController.repo.delete(course.getCourseId());
                CourseRepo.courses.remove(course);
                return true;
            }
            else {
                System.out.println("Profesorul nu are un astfel de curs!");
                return false;
            }
        }
        else {
            System.out.println("Nu a fost gasit profesorul!");
            return false;
        }
    }


}

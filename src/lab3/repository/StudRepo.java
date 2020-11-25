package lab3.repository;

import lab3.classes.Student;

import java.util.ArrayList;
import java.util.List;

public class StudRepo implements ICrudRepository<Student>{

    public List<Student> students = new ArrayList<>();

    public StudRepo() {
        super();
    }

    @Override
    public Student findOne(Long id) {
        for(Student stud : students)
            if(stud.getStudentId() == id)
                return stud;
        return null;
    }

    @Override
    public Iterable<Student> findAll() {
        return students;
    }

    @Override
    public Student save(Student entity) {
        if(entity == null)
            return null;
        for(Student stud : students)
            if(stud == entity)
                return entity;
        students.add(entity);

        return null;
    }

    @Override
    public Student delete(Long id) {
        for(Student stud:students)
            if(stud.getStudentId() == id) {
                students.remove(stud);
                return stud;
            }
        return null;
    }

    @Override
    public Student update(Student entity) {
        for(Student stud:students)
            if(stud.getStudentId() == entity.getStudentId()) {
                students.set(students.indexOf(stud), entity);
                return null;
            }
        return entity;
    }
}

package lab3.repository;

import lab3.classes.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepo implements ICrudRepository<Teacher>{

    public List<Teacher> teachers = new ArrayList<>();

    @Override
    public Teacher findOne(Long id) {
        for(Teacher t:teachers)
            if(t.getTeacherId() == id)
                return t;
        return null;
    }

    @Override
    public Iterable<Teacher> findAll() {
        return teachers;
    }

    @Override
    public Teacher save(Teacher entity) {
        for(Teacher t:teachers)
            if(t.getTeacherId() == entity.getTeacherId()) {
                return entity;
            }
        teachers.add(entity);
        return null;
    }

    @Override
    public Teacher delete(Long id) {
        for(Teacher t:teachers)
            if(t.getTeacherId() == id) {
                teachers.remove(t);
                return t;
            }
        return null;
    }

    @Override
    public Teacher update(Teacher entity) {
        for(Teacher t:teachers)
            if(t.getTeacherId() == entity.getTeacherId()) {
                teachers.set(teachers.indexOf(t), entity);
                return null;
            }
        return entity;
    }
}
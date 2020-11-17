package lab3.controller;

import lab3.classes.Teacher;
import lab3.repository.ICrudRepository;
import lab3.repository.TeacherRepo;

public class TeacherController implements ICrudRepository<Teacher> {

    TeacherRepo repo = new TeacherRepo();

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
}

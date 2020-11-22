package lab3.controller;

import lab3.classes.Student;
import lab3.repository.ICrudRepository;
import lab3.repository.StudRepo;

public class StudController implements ICrudRepository<Student> {
    public StudRepo repo = new StudRepo();

    @Override
    public Student findOne(Long id) {   return repo.findOne(id); }

    @Override
    public Iterable<Student> findAll() {    return repo.findAll(); }

    @Override
    public Student save(Student entity) {   return repo.save(entity); }

    @Override
    public Student delete(Long id) {    return repo.delete(id); }

    @Override
    public Student update(Student entity) { return repo.update(entity); }
}

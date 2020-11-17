package lab3.controller;

import lab3.classes.Course;
import lab3.repository.CourseRepo;
import lab3.repository.ICrudRepository;

public class CourseController implements ICrudRepository<Course> {

    CourseRepo repo = new CourseRepo();

    @Override
    public Course findOne(Long id) {    return repo.findOne(id); }

    @Override
    public Iterable findAll() {     return repo.findAll(); }

    @Override
    public Course save(Course entity) { return repo.save(entity); }

    @Override
    public Course delete(Long id) { return repo.delete(id); }

    @Override
    public Course update(Course entity) {   return repo.update(entity); }
}

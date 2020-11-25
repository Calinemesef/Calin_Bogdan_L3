package lab3.controller;

import lab3.classes.Course;
import lab3.repository.CourseRepo;
import lab3.repository.ICrudRepository;

import java.util.Comparator;
import java.util.stream.Collectors;

public class CourseController implements ICrudRepository<Course> {

    public CourseRepo repo = new CourseRepo();

    @Override
    public Course findOne(Long id) {    return repo.findOne(id); }

    @Override
    public Iterable<Course> findAll() {     return repo.findAll(); }

    @Override
    public Course save(Course entity) { return repo.save(entity); }

    @Override
    public Course delete(Long id) { return repo.delete(id); }

    @Override
    public Course update(Course entity) {   return repo.update(entity); }

    /**
     *  Methode fur Sortieren der Kursen aus dem Repo nach Name
     */
    void sortByName(){
        repo.courses.sort(Comparator.comparing(Course::getNume));
    }

    /**
     *  Methode fur Filtrieren der Kursen aus dem Repo nach Kreditanzahl, es bleiben nur diejenigen mit credits <8
     */
    void filterCredits(){
        repo.courses = repo.courses.stream().filter(curs -> curs.getCredits() < 8).collect(Collectors.toList());
    }

}

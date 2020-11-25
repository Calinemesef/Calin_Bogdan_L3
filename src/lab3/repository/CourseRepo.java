package lab3.repository;

import lab3.classes.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepo implements ICrudRepository<Course> {

    public List<Course> courses = new ArrayList<>();

    public boolean isEmpty(){
        return courses.size() == 0;
    }

    @Override
    public Course findOne(Long id) {
        for(Course c:courses)
            if(c.getCourseId() == id)
                return c;
        return null;
    }

    @Override
    public Iterable<Course> findAll() { return courses; }

    @Override
    public Course save(Course entity) {
        for(Course c:courses)
            if(c.getCourseId() == entity.getCourseId())
                return entity;
        courses.add(entity);
        return null;
    }

    @Override
    public Course delete(Long id) {
        for(Course c:courses)
            if(c.getCourseId() == id) {
                courses.remove(c);
                return c;
            }
        return null;
    }

    @Override
    public Course update(Course entity) {
        for(Course c:courses)
            if(c.getCourseId() == entity.getCourseId())
            {
                courses.set(courses.indexOf(c), entity);
                return null;
            }
        return entity;
    }

    public int size() {
        return courses.size();
    }
}

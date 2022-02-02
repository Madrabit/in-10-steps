package ru.madrabot.hibernate.repository;

import org.springframework.stereotype.Repository;
import ru.madrabot.hibernate.entity.Course;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    private final EntityManager em;

    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course create(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void delete(Long id) {
        final Course course = findById(id);
        em.remove(course);
    }
}

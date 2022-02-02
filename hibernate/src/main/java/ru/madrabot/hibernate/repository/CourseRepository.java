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

    public Course save(Course course) {
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

    public void playWithEntityManager() {
        final Course course1 = new Course("Test 1 Course");
        em.persist(course1);
        // push to DB
        em.flush();
        course1.setName("Test 1 Course - Updated");

        final Course course2 = new Course("Test 2 Course");
        em.persist(course2);
        em.flush();

        // Detach course2 from PersistentContext
        em.detach(course2);
        course2.setName("Test 2 Course - Updated");

        final Course course3 = new Course("Test 3 Course");
        em.persist(course3);
        final Course course4 = new Course("Test 4 Course");
        em.persist(course4);
        em.flush();

        //Detach all from Persistent context
        em.clear();

        course3.setName("Test 3 Course - Updated");
        course4.setName("Test 4 Course - Updated");

        // Attach back course4
        em.merge(course4);
        course4.setName("Test 4 Course - Updated");

        final Course course5 = new Course("Test 5 Course");
        em.persist(course5);
        em.flush();
        course5.setName("Test 5 Course - Updated");
        // Synchronize Entity from DB.
        em.refresh(course5);
        em.flush();
    }

    public void createAndUpdateDateView() {
    }
}

package ru.madrabot.hibernate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.entity.Student;
import ru.madrabot.hibernate.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class JPQLTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void jpql_base() {
        final List list = em.createNamedQuery("query_get_all_courses").getResultList();
        logger.info("Base select {}", list);
    }

    @Test
    void jpql_typed() {
        final TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        final List<Course> list = query.getResultList();
        logger.info("Typed select {}", list);
    }

    @Test
    void jpql_typed_where() {
        final TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses_like", Course.class);
        final List<Course> list = query.getResultList();
        logger.info("Typed select where first {}", list);
    }

    @Test
    void jpql_retrieveAllCoursesWithoutStudents() {
        final TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.students is empty ", Course.class);
        final List<Course> list = query.getResultList();
        logger.info("Select courses without students  {}", list);
    }

    @Test
    void jpql_retrieveAllCoursesAtLeast2Students() {
        final TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE size(c.students) >= 2 ", Course.class);
        final List<Course> list = query.getResultList();
        logger.info("Select courses without students  {}", list);
    }

    @Test
    void jpql_retrieveAllCourseOrderedByStudentsDesc() {
        final TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c order by size(c.students) desc ", Course.class);
        final List<Course> list = query.getResultList();
        logger.info("Select courses without students  {}", list);
    }

    @Test
    void jpql_retrieveStudentByConcretePassport() {
        final TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%L%'", Student.class);
        final List<Student> list = query.getResultList();
        logger.info("Student with concrete passport  {}", list);
    }

    @Test
    void jpql_left_join() {
        final Query query = em.createQuery("SELECT c, s FROM Course c LEFT JOIN c.students s");
        final List<Object[]> resultList = query.getResultList();
        for (Object[] result : resultList) {
            logger.info("{} {}", result[0], result[1]);
        }

    }


}

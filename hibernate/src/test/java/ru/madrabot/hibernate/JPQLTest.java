package ru.madrabot.hibernate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.repository.CourseRepository;

import javax.persistence.EntityManager;
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


}

package ru.madrabot.hibernate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest
class CriteriaTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void criteria_base() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        final Root<Course> courseRoot = cq.from(Course.class);
//        final Predicate predicate = cb.like(courseRoot.get("name"), "%First%");
//        final Predicate predicate = cb.isEmpty(courseRoot.get("students"));
//        cq.where(predicate);
        courseRoot.join("students", JoinType.LEFT);
        final List<Course> list = em.createQuery(cq.select(courseRoot)).getResultList();
        logger.info("Select {}", list);
    }


}

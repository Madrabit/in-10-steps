package ru.madrabot.hibernate.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.entity.Review;
import ru.madrabot.hibernate.entity.Student;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PerformanceTuningTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        final List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        for (Course course : courses) {
            logger.info("Get student from courses {} ", course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph() {
        final EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students").addSubgraph("passport");
        final List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        for (Course course : courses) {
            logger.info("Get student from courses {} ", course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch() {
        final List<Course> courses = em.createNamedQuery("query_get_all_courses_joined_students", Course.class)
                .getResultList();
        for (Course course : courses) {
            logger.info("Get student from courses {} ", course.getStudents());
        }
    }

}

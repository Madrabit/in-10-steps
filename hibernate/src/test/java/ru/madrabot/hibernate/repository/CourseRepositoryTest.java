package ru.madrabot.hibernate.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.entity.Review;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    void findById() {
        final Course course = courseRepository.findById(1001L);
        assertEquals("First course", course.getName());
    }

    @Test
    @DirtiesContext
    void delete() {
        courseRepository.delete(1001L);
        assertNull(courseRepository.findById(1001L));
    }

    @Test
    @DirtiesContext
    void save() {
        final Course course = courseRepository.findById(1001L);
        assertEquals("First course", course.getName());
        course.setName("First course - Updated");
        courseRepository.save(course);
        final Course course2 = courseRepository.findById(1001L);
        assertEquals("First course - Updated", course2.getName());
    }

    @Test
    @DirtiesContext
    public void emTest() {
        courseRepository.playWithEntityManager();
    }


    //**toMany default Lazy. That's why set up Eager
    @Test
    @Transactional
    public void retrieveReviewsFromCourse() {
        final Course course = courseRepository.findById(1001L);
        System.out.println(course.getReviews());
        logger.info("Retrieve reviews from courses", course.getReviews());
    }


    //**toOne default Eager.
    @Test
    @Transactional
    public void retrieveCourseFromReview() {
        final Review review = em.find(Review.class, 4001L);
        System.out.println(review.getCourse());
        logger.info("Retrieve courses from review", review.getCourse());
    }
}

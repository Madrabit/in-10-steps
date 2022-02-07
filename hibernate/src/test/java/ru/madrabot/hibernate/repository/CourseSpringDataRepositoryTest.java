package ru.madrabot.hibernate.repository;

import org.hibernate.annotations.Sort;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import ru.madrabot.hibernate.entity.Course;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseSpringDataRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Autowired
    EntityManager em;

    @Test
    public void findById() {
        final Optional<Course> course = repository.findById(1001L);
        assertTrue(course.isPresent());
    }

    @Test
    @Transactional
    public void playGround() {
//        repository.save(new Course("New test course"));
        repository.deleteById(1001L);
        logger.info("count {}", repository.count());
    }

    @Test
    public void sort() {
        logger.info("find All by Asc {}", repository.findByOrderByNameAsc());
        logger.info("find All by Desc {}", repository.findByOrderByNameDesc());
    }

    @Test
    public void pagination() {
        PageRequest firstPage = PageRequest.of(0,3);
        logger.info("Get 1 page {}", repository.findAll(firstPage).getContent());
        PageRequest secondPage = firstPage.next();
        logger.info("Get next page {}", repository.findAll(secondPage).getContent());
    }

}

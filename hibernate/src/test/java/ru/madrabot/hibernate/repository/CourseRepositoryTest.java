package ru.madrabot.hibernate.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.madrabot.hibernate.entity.Course;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

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
}

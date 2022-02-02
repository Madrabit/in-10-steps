package ru.madrabot.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.repository.CourseRepository;


@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository courseRepository;

    public HibernateApplication(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final Course course = courseRepository.findById(1001L);
        logger.info("Get course by Id {}", course);
        courseRepository.save(new Course("Microsoft course"));
        courseRepository.playWithEntityManager();
    }
}

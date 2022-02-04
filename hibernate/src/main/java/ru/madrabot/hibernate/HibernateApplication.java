package ru.madrabot.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.entity.Review;
import ru.madrabot.hibernate.entity.Student;
import ru.madrabot.hibernate.repository.CourseRepository;
import ru.madrabot.hibernate.repository.StudentRepository;

import java.util.List;


@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;


    public HibernateApplication(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.insertStudentAndCourse(new Student("Test name std"), new Course("Some Course"));
    }
}

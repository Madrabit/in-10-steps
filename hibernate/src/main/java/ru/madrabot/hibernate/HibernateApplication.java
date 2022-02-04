package ru.madrabot.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.madrabot.hibernate.entity.*;
import ru.madrabot.hibernate.repository.CourseRepository;
import ru.madrabot.hibernate.repository.EmployeeRepository;
import ru.madrabot.hibernate.repository.StudentRepository;

import java.math.BigDecimal;


@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;


    public HibernateApplication(CourseRepository courseRepository, StudentRepository studentRepository,
                                EmployeeRepository employeeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.employeeRepository = employeeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee fullTime = new FullTimeEmployee("Jhon", new BigDecimal("1000"));
        Employee partTime = new PartTimeEmployee("Mike", new BigDecimal("50"));
        employeeRepository.save(fullTime);
        employeeRepository.save(partTime);
        logger.info("All employees -> {}", employeeRepository.retrieveAllEmployees());

    }
}

package ru.madrabot.hibernate.repository;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabot.hibernate.entity.Passport;
import ru.madrabot.hibernate.entity.Student;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@SpringBootTest
class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    //Eager fetching passport
    @Test
    @Transactional
    public void retrieveStudentWithPassport() {
        Student student = em.find(Student.class, 2001L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrievePassportWithStudent() {
        Passport passport = em.find(Passport.class, 3001L);
        logger.info("student -> {}", passport);
        logger.info("passport -> {}", passport.getStudent());
    }

}

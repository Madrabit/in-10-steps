package ru.madrabit.databasedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import ru.madrabit.databasedemo.entity.Person;
import ru.madrabit.databasedemo.jpa.PersonJpaDao;

import java.util.Date;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class JPADemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonJpaDao personJpaDao;

    public JPADemoApplication(PersonJpaDao personJpaDao) {
        this.personJpaDao = personJpaDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(JPADemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Insert user 1005 => {}", personJpaDao.update(new Person("Vasya", "Kiev", new Date())));
        logger.info("Get user by Id => {}", personJpaDao.findPersonById(1));
        logger.info("Insert user 1004 => {}", personJpaDao.update(new Person( "Petya", "Moscow", new Date())));
        personJpaDao.delete(1);
        logger.info("Find all Persons => {}", personJpaDao.findAll());

    }
}

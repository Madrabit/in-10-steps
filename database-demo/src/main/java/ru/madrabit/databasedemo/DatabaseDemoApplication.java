package ru.madrabit.databasedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.RowMapper;
import ru.madrabit.databasedemo.entity.Person;
import ru.madrabit.databasedemo.jdbc.PersonJdbcDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DatabaseDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users => {}", personJdbcDao.findAll());
        logger.info("User 1001 => {}", personJdbcDao.findById(1001));
        logger.info("Delete user 1002 => {}", personJdbcDao.delete(1002));
//        logger.info("Creat new user => {}", personJdbcDao.create(new Person(1005, "Vasya", "Kiev", new Date())));
//        logger.info("Creat new user => {}", personJdbcDao.update(new Person(1001,"Ira", "Siberia", new Date())));

    }
}

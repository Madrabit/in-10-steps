package ru.madrabot.hibernate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabot.hibernate.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class NativeQueriesTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void native_base() {
        final List list = em.createNativeQuery("SELECT * FROM course", Course.class).getResultList();
        logger.info("Base select {}", list);
    }

    @Test
    void native_base_with_parameter() {
        final Query query = em.createNativeQuery("SELECT * FROM course WHERE id=?", Course.class);
        query.setParameter(1,1001L);
        final List list = query.getResultList();
        logger.info("Select with parameter {}", list);
    }

    @Test
    void native_base_with_named_parameter() {
        final Query query = em.createNativeQuery("SELECT * FROM course WHERE id=:id", Course.class);
        query.setParameter("id",1001L);
        final List list = query.getResultList();
        logger.info("Select with parameter {}", list);
    }

    @Test
    @Transactional
    void native_update() {
        final Query query = em.createNativeQuery("UPDATE course set last_updated_date=sysdate()", Course.class);
        final int numbersOfRows = query.executeUpdate();
        logger.info("Native Update {}", numbersOfRows);
    }


}

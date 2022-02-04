package ru.madrabot.hibernate.repository;

import org.springframework.stereotype.Repository;
import ru.madrabot.hibernate.entity.Employee;
import ru.madrabot.hibernate.entity.FullTimeEmployee;
import ru.madrabot.hibernate.entity.PartTimeEmployee;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private final EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Employee employee) {
        em.persist(employee);
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
       return em.createQuery("select e From FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
        return em.createQuery("select e From PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }


}

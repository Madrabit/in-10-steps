package ru.madrabot.hibernate.repository;

import org.springframework.stereotype.Repository;
import ru.madrabot.hibernate.entity.Employee;

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

    public List<Employee> retrieveAllEmployees() {
       return em.createQuery("select e From Employee e", Employee.class).getResultList();
    }
}

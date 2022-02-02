package ru.madrabit.databasedemo.jpa;

import org.springframework.stereotype.Repository;
import ru.madrabit.databasedemo.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaDao {


    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findAll() {
        final TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
        return namedQuery.getResultList();
    }

    public Person findPersonById(int id) {
       return entityManager.find(Person.class, id);
    }

    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public void delete(int id) {
        Person person = findPersonById(id);
        entityManager.remove(person);
    }
}

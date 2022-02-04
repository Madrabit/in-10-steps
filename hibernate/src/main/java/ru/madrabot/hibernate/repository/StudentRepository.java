package ru.madrabot.hibernate.repository;

import org.springframework.stereotype.Repository;
import ru.madrabot.hibernate.entity.Course;
import ru.madrabot.hibernate.entity.Passport;
import ru.madrabot.hibernate.entity.Student;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    private final EntityManager em;

    public StudentRepository(EntityManager em) {
        this.em = em;
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void delete(Long id) {
        final Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("G7777");
        em.persist(passport);
        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void playWithEntityManager() {
        final Student student1 = new Student("Test 1 Student");
        em.persist(student1);
        // push to DB
        em.flush();
        student1.setName("Test 1 Student - Updated");

        final Student student2 = new Student("Test 2 Student");
        em.persist(student2);
        em.flush();

        // Detach student2 from PersistentContext
        em.detach(student2);
        student2.setName("Test 2 Student - Updated");

        final Student student3 = new Student("Test 3 Student");
        em.persist(student3);
        final Student student4 = new Student("Test 4 Student");
        em.persist(student4);
        em.flush();

        //Detach all from Persistent context
        em.clear();

        student3.setName("Test 3 Student - Updated");
        student4.setName("Test 4 Student - Updated");

        // Attach back student4
        em.merge(student4);
        student4.setName("Test 4 Student - Updated");

        final Student student5 = new Student("Test 5 Student");
        em.persist(student5);
        em.flush();
        student5.setName("Test 5 Student - Updated");
        // Synchronize Entity from DB.
        em.refresh(student5);
        em.flush();
    }

    public void insertStudentAndCourse(Student student, Course course) {
        student.addCourses(course);
        course.addStudents(student);
        em.persist(course);
        em.persist(student);
    }
}

package ru.madrabot.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.madrabot.hibernate.entity.Course;

import java.util.List;

@RepositoryRestResource(path="test")
public interface CourseSpringDataRepository  extends JpaRepository<Course, Long> {
    List<Course> findByOrderByNameAsc();

    List<Course> findByOrderByNameDesc();

    @Query(value = "SELECT c FROM Course c WHERE c.name LIKE 'First course'")
    List<Course> findLikeJpql();

    @Query(value = "SELECT * FROM Course as c WHERE c.name LIKE 'First course'", nativeQuery = true)
    List<Course> findLikeNativeQuery();

    @Query(name="query_get_all_courses_like")
    List<Course> findLikeNamedQuery();
}

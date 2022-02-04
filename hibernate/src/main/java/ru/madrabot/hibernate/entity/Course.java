package ru.madrabot.hibernate.entity;


import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Course")
@NamedQueries(
        value = {
                @NamedQuery(name = "query_get_all_courses", query = "SELECT c FROM Course c"),
                @NamedQuery(name = "query_get_all_courses_like", query = "SELECT c FROM Course c WHERE c.name LIKE 'First course'")
        }
)

public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="COURSE_STUDENT",
    joinColumns = @JoinColumn(name="COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name="STUDENT_ID")
    )
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String name) {
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        this.students.add(student);
    }

    public void removeStudents(Student student) {
        this.students.remove(student);
    }

    @Override
    public String toString() {
        return "\nCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.Junit.Student.Repo;

import com.Junit.Student.Entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends MongoRepository<Student,Long> {
    boolean existsByEmail(String email);

    List<Student> findByCourse(String course);

    List<Student> findByAgeGreaterThan(Integer age);

    List<Student> findByNameContainingIgnoreCase(String name);

    long countByCourse(String course);
}

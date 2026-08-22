package com.Junit.Student.Services;

import com.Junit.Student.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudentById(Long id);



    List<Student> getAllStudents();

    Student updateStudent(Long id, Student student);



    void deleteStudent(Long id);



    boolean existsByEmail(String email);

    List<Student> getStudentsByCourse(String course);

    List<Student> getStudentsOlderThan(Integer age);

    List<Student> searchStudentsByName(String name);

    long countStudentsByCourse(String course);

    double calculateAverageAge();


    Student promoteStudent(Long id, double bonusMarks);
}

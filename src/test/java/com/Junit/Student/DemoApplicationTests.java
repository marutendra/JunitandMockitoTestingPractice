package com.Junit.Student;

import com.Junit.Student.Entity.Student;
import com.Junit.Student.Repo.StudentRepository;
import com.Junit.Student.Services.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    StudentServiceImpl service;

    Student student= Student.builder()
            .id(102l)
            .name("alex")
            .age(19)
            .marks(78.9)
            .email("abc@!23")
            .course("btech")
            .build();

// Testing 1
	@Test
    void createStudentTest1() {
        when(studentRepository.existsByEmail(student.getEmail()))
                .thenReturn(false);
        when(studentRepository.save(student)).thenReturn(student);
        Student student1 = service.createStudent(student);
        verify(studentRepository).existsByEmail(student.getEmail());
        verify(studentRepository).save(student);

    }
    @Test
    void createStudentTest2() {
        when(studentRepository.existsByEmail(student.getEmail()))
                .thenReturn(true);

        assertThrows(RuntimeException.class,()->service.createStudent(student));
        verify(studentRepository).existsByEmail(student.getEmail());
        verify(studentRepository, never())
                .save(any(Student.class));

    }

    // Testing 2

    @Test
void finndbyidTest(){
        //   Student student3= Student.builder().name("aa").id(102L).build();
        when(studentRepository.findById(102L)).thenReturn(Optional.of(student));
        Student studentById = service.getStudentById(102L);
        // for verification if our method is exactly called or not
        verify(studentRepository).findById(102L);
       assertEquals(student,studentById);

       // here we can test for two conditions one is for if ID is found and one is for when
        // id is not found in this case we will throw an exception like this
        //   assertThrows(RuntimeException.class,()->service.getStudentById(103L));

    }

    // TESTING 3


    Student studentobject1 = Student.builder()
            .id(102L)
            .name("Sunny")
            .age(22)
            .course("Java")
            .build();
    Student studentobject2 = Student.builder()
            .id(103L)
            .name("Sunny")
            .age(22)
            .course("Java")
            .build();
List<Student> students=List.of(studentobject1,studentobject2);
@Test
void getAllStudentTest(){
    when(studentRepository.findAll()).thenReturn(students);
    List<Student> allStudents = service.getAllStudents();
    assertEquals(students,allStudents);
    verify(studentRepository).findAll();

}
    Student studentforupdate= Student.builder()
            .id(102l)
            .name("alex")
            //.age(19)
            .marks(78.9)
            .email("abc@!23")
            .course("btech")
            .build();
//    Student student= Student.builder()
//            .id(102l)
//            .name("alex")
//            .age(19)
//            .marks(78.9)
//            .email("abc@!23")
//            .course("btech")
//            .build();


// Test 4
    @Test
    void updateStudentTest(){
        Student student = Student.builder()
                .id(102L)
                .name("alex")
                .age(19)
                .marks(78.9)
                .email("abc@!23")
                .course("btech")
                .build();

        Student expectedStudent = Student.builder()
                .id(102L)
                .name("alex")
                .age(19)
                .marks(78.9)
                .email("abc@!23")
                .course("btech")
                .build();

        when(studentRepository.findById(102L))
                .thenReturn(Optional.of(student));

        when(studentRepository.save(student))
                .thenReturn(student);

        Student result = service.updateStudent(102L, student);

        assertEquals(expectedStudent, result);
    }






}

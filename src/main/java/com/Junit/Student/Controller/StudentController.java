package com.Junit.Student.Controller;




import com.Junit.Student.Entity.Student;
import com.Junit.Student.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(
                studentService.existsByEmail(email));
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<List<Student>> getByCourse(
            @PathVariable String course) {

        return ResponseEntity.ok(
                studentService.getStudentsByCourse(course));
    }

    @GetMapping("/older-than/{age}")
    public ResponseEntity<List<Student>> getOlderThan(
            @PathVariable Integer age) {

        return ResponseEntity.ok(
                studentService.getStudentsOlderThan(age));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                studentService.searchStudentsByName(name));
    }

    @GetMapping("/count/{course}")
    public ResponseEntity<Long> countByCourse(
            @PathVariable String course) {

        return ResponseEntity.ok(
                studentService.countStudentsByCourse(course));
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> averageAge() {

        return ResponseEntity.ok(
                studentService.calculateAverageAge());
    }

    @PatchMapping("/{id}/promote")
    public ResponseEntity<Student> promoteStudent(
            @PathVariable Long id,
            @RequestParam double bonusMarks) {

        return ResponseEntity.ok(
                studentService.promoteStudent(id, bonusMarks));
    }
}
package com.Junit.Student.Services;

import com.Junit.Student.Entity.Student;
import com.Junit.Student.Repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class StudentServiceImpl implements StudentService {

        private final StudentRepository studentRepository;

        public StudentServiceImpl(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }
        // M-1

        @Override
        public Student createStudent(Student student) {

            if (studentRepository.existsByEmail(student.getEmail())) {
                throw new RuntimeException("Email already exists");
            }

            return studentRepository.save(student);
        }


        // M-2
        @Override
        public Student getStudentById(Long id) {

            return studentRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Student not found"));
        }
        // M-3

        @Override
        public List<Student> getAllStudents() {

            return studentRepository.findAll();
        }
        @Override
        public Student updateStudent(Long id, Student student) {

            Student existingStudent = studentRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Student not found"));
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setAge(student.getAge());
            existingStudent.setCourse(student.getCourse());
            existingStudent.setMarks(student.getMarks());

            return studentRepository.save(existingStudent);
        }



        @Override
        public void deleteStudent(Long id) {

            if (!studentRepository.existsById(id)) {
                throw new RuntimeException("Student not found");
            }

            studentRepository.deleteById(id);
        }

        @Override
        public boolean existsByEmail(String email) {

            return studentRepository.existsByEmail(email);
        }

        @Override
        public List<Student> getStudentsByCourse(String course) {

            return studentRepository.findByCourse(course);
        }

        @Override
        public List<Student> getStudentsOlderThan(Integer age) {

            return studentRepository.findByAgeGreaterThan(age);
        }

        @Override
        public List<Student> searchStudentsByName(String name) {

            return studentRepository.findByNameContainingIgnoreCase(name);
        }

        @Override
        public long countStudentsByCourse(String course) {

            return studentRepository.countByCourse(course);
        }

        @Override
        public double calculateAverageAge() {

            List<Student> students = studentRepository.findAll();

            if (students.isEmpty()) {
                return 0;
            }

            int totalAge = students.stream()
                    .mapToInt(Student::getAge)
                    .sum();

            return (double) totalAge / students.size();
        }



        @Override
        public Student promoteStudent(Long id, double bonusMarks) {

            Student student = studentRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Student not found"));

            double newMarks = student.getMarks() + bonusMarks;

            if (newMarks > 100) {
                newMarks = 100;
            }

            student.setMarks(newMarks);

            return studentRepository.save(student);
        }
    }


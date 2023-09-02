package ru.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.StudentService;

import java.io.StringReader;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        if(id == null && studentService.getStudent(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping("/last-5")
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        return ResponseEntity.ok().body(studentService.getLastFiveStudents());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getAllStudentsCount() {
        return ResponseEntity.ok().body(studentService.getAllStudentsCount());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student editStudent(@PathVariable Long id,
                               @RequestBody Student student) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/all")
    public Collection<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/by-age/{age}")
    public Collection<Student> getAllOfAge(@PathVariable int age) {
        return studentService.getStudentsOfAge(age);
    }

    @GetMapping("/avg-age")
    public ResponseEntity<Double> getStudentsAvgAge() {
        return ResponseEntity.ok().body(studentService.getStudentsAvgAge());
    }

    @GetMapping("/avg-age-№2")
    public ResponseEntity<Double> getStudentsAvgAge_2() {
        return ResponseEntity.ok().body(studentService.getStudentsAvgAge_2());
    }

    @GetMapping("/age-between")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam(required = false) Integer min,
                                                       @RequestParam(required = false) Integer max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("/of-faculty/{facultyId}")
    public Collection<Student> getStudentsOfFaculty(@PathVariable Long facultyId) {
        return studentService.getStudentsOfFaculty(facultyId);
    }

    @GetMapping("/name-starts-with/{letter}")
    public Collection<String> getStudentsByNameLetter(@PathVariable String letter) {
        return studentService.getStudentsByNameLetter(letter);
    }
}



package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.AgeLessThanZeroException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student editStudent(Long id, Student student) {
        Student findedStudent = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        findedStudent.setName(student.getName());
        findedStudent.setAge(student.getAge());
        return studentRepository.save(findedStudent);
    }

    public Student deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }

    public int getAllStudentsCount() {
        return studentRepository.getAllStudentsCount();
    }

    public Collection<Student> getStudentsOfAge(int age) {
        if (age < 0) {
            throw new AgeLessThanZeroException();
        }
        return studentRepository.findAllByAge(age);
    }

    public int getStudentsAvgAge() {
        return studentRepository.getStudentsAvgAge();
    }

    public Collection<Student> getStudentsByAgeBetween(Integer min, Integer max) {
        if(min == null) {
            return studentRepository.getStudentsByAgeBetween(0, max);
        }
        if(max == null) {
            return studentRepository.getStudentsByAgeBetween(min, 300);
        }
        return studentRepository.getStudentsByAgeBetween(min, max);
    }

    public Collection<Student> getStudentsOfFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).map(Faculty::getStudents).orElseThrow();
    }
}

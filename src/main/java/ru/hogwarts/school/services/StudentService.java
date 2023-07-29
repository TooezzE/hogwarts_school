package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.AgeLessThanZeroException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentsRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentsRepository studentsRepository;

    public StudentService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student createStudent(Student student) {
        return studentsRepository.save(student);
    }

    public Optional<Student> getStudent(long id) {
        return studentsRepository.findById(id);
    }

    public Student editStudent(Student student) {
        return studentsRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentsRepository.deleteById(id);

    }

    public Collection<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Collection<Student> getStudentsOfAge(int age) {
        if (age < 0) {
            throw new AgeLessThanZeroException();
        }
        return studentsRepository.findAllByAge(age);
    }

    public Collection<Student> getStudentsByAgeBetween(Integer min, Integer max) {
        if(min == null) {
            return studentsRepository.getStudentsByAgeBetween(0, max);
        }
        if(max == null) {
            return studentsRepository.getStudentsByAgeBetween(min, 300);
        }
        return studentsRepository.getStudentsByAgeBetween(min, max);
    }
}

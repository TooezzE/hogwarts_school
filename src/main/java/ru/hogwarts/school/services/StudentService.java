package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentsRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentsRepository studentsRepository;

    public StudentService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student createStudent(Student student) {
        return studentsRepository.save(student);
    }

    public Student getStudent(long id) {
        return studentsRepository.findById(id).get();
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
}

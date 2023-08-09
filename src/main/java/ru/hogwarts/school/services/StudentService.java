package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.AgeLessThanZeroException;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentsRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentsRepository studentsRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentsRepository studentsRepository, FacultyRepository facultyRepository) {
        this.studentsRepository = studentsRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        return studentsRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentsRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student editStudent(Long id, Student student) {
        Student findedStudent = studentsRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        findedStudent.setName(student.getName());
        findedStudent.setAge(student.getAge());
        return studentsRepository.save(findedStudent);
    }

    public Student deleteStudent(Long id) {
        Student student = studentsRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentsRepository.delete(student);
        return student;
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

    public Collection<Student> getStudentsOfFaculty(Long facultyId) {
        return facultyRepository.getStudentsOfFaculty(facultyId);
    }
}

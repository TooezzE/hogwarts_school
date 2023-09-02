package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.AgeLessThanZeroException;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("Was invoked method for get student");
        Student student = null;
        try {
            student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        } catch (StudentNotFoundException e) {
            logger.error("There is not student with id = " + id);
        }
        return student;
    }

    public Student editStudent(Long id, Student student) {
        logger.info("Was invoked method for edit student");
        Student findedStudent = null;
        try {
            findedStudent = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
            findedStudent.setName(student.getName());
            findedStudent.setAge(student.getAge());
        } catch (StudentNotFoundException e) {
            logger.error("There is not student with id = " + id);
        }
        return studentRepository.save(findedStudent);
    }

    public Student deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");
        Student student = null;
        try {
            student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
            studentRepository.delete(student);
        } catch (StudentNotFoundException e) {
            logger.error("There is not student with id = " + id);
        }
        return student;
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Collection<Student> getLastFiveStudents() {
        logger.info("Was invoked method for get last 5 students");
        return studentRepository.getLastFiveStudents();
    }

    public int getAllStudentsCount() {
        logger.info("Was invoked method for get count of students");
        return studentRepository.getAllStudentsCount();
    }

    public Collection<Student> getStudentsOfAge(int age) {
        logger.info("Was invoked method for get students of age " + age);
        if (age < 0) {
            logger.error("Age " + age + " less than 0");
            throw new AgeLessThanZeroException();
        }
        return studentRepository.findAllByAge(age);
    }

    public double getStudentsAvgAge() {
        logger.info("Was invoked method for get students average age");
        return studentRepository.getStudentsAvgAge();
    }

    public double getStudentsAvgAge_2() {
        logger.info("Was invoked method for get students average age №2");
        return studentRepository.findAll().stream()
                .map(Student::getAge)
                .mapToInt(a -> a)
                .average().orElse(0);
    }

    public Collection<Student> getStudentsByAgeBetween(Integer min, Integer max) {
        logger.info("Was invoked method for get students be age between " + min + " and " + max);
        if(min == null) {
            return studentRepository.getStudentsByAgeBetween(0, max);
        }
        if(max == null) {
            return studentRepository.getStudentsByAgeBetween(min, 300);
        }
        return studentRepository.getStudentsByAgeBetween(min, max);
    }

    public Collection<Student> getStudentsOfFaculty(Long facultyId) {
        logger.info("Was invoked method for get students of faculty with id " + facultyId);
        Collection<Student> studentsOfFaculty = null;
        try {
            studentsOfFaculty = facultyRepository.findById(facultyId)
                    .map(Faculty::getStudents)
                    .orElseThrow(FacultyNotFoundException::new);
        } catch (FacultyNotFoundException e) {
            logger.error("There is not faculty with id = " + facultyId);
        }
        return studentsOfFaculty;
    }

    public Collection<String> getStudentsByNameLetter(String letter) {
        logger.info("Was invoked method for get students whose name starts with \"" + letter.toUpperCase() + "\"");
        if(!letter.matches("[a-zA-Z]+")) {
            logger.error("Given \n" + letter + "\" is not a letter");
            throw new IllegalArgumentException("It's not a letter");
        }
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(n -> n.startsWith(letter.toUpperCase()))
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}

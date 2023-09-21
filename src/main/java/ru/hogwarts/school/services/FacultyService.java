package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    // Create faculty
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    // Get faculty
    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method for get faculty");
        Faculty faculty = null;
        try {
            faculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        } catch (FacultyNotFoundException e) {
            logger.error("There is not faculty with id = " + id);
        }
        return faculty;
    }

    // Edit faculty
    public Faculty editFaculty(Long id, Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        Faculty findedFaculty = null;
        try {
            findedFaculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
            findedFaculty.setName(faculty.getName());
            findedFaculty.setColor(faculty.getColor());
        } catch (FacultyNotFoundException e) {
            logger.error("There is not faculty with id = " + id);
        }
        return facultyRepository.save(findedFaculty);
    }

    // Delete faculty
    public Faculty deleteFaculty(Long id) {
        logger.info("Was invoked method for edit faculty");
        Faculty faculty = null;
        try {
            faculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
            facultyRepository.deleteById(id);
        } catch (FacultyNotFoundException e) {
            logger.error("There is not faculty with id = " + id);
        }
        return faculty;
    }

    // Get all faculties
    public Collection<Faculty> getAll() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    // Get faculties of color
    public Collection<Faculty> getFacultiesOfColor(String color) {
        logger.info("Was invoked method for get faculties of color " + color);
        return facultyRepository.findAllByColorIgnoreCase(color);
    }

    // Get faculty by name
    public Faculty getFacultyByName(String name) {
        logger.info("Was invoked method for get faculty by name " + name);
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    // Find faculty by student in
    public Faculty getFacultyByStudentId(Long studentId) {
        logger.info("Was invoked method for get faculty by student id " + studentId);
        Faculty faculty = null;
        try {
            faculty = studentRepository.findById(studentId)
                    .map(Student::getFaculty)
                    .orElseThrow(FacultyNotFoundException::new);
        } catch (FacultyNotFoundException e) {
            logger.error("There is not faculty which contains student with id = " + studentId);
        }
        return faculty;
    }

    // Get faculty with the longest name
    public String getTheLongestFacultyName() {
        logger.info("Was invoked method for get the longest faculty name");
        return facultyRepository.findAll().stream()
                .map(f -> f.getName())
                .max(Comparator.comparing(String::valueOf))
                .orElseThrow();
    }
}

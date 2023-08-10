package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentsRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentsRepository studentsRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentsRepository studentsRepository) {
        this.facultyRepository = facultyRepository;
        this.studentsRepository = studentsRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        Faculty findedFaculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        findedFaculty.setName(faculty.getName());
        findedFaculty.setColor(faculty.getColor());
        return facultyRepository.save(findedFaculty);
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        facultyRepository.deleteById(id);
        return faculty;
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultiesOfColor(String color) {
        return facultyRepository.findAllByColorIgnoreCase(color);
    }

    public Faculty getFacultyByName(String name) {
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    public Faculty getFacultyByStudentId(Long studentId) {
        return studentsRepository.findById(studentId).map(Student::getFaculty).orElseThrow();
    }

}

package ru.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.services.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}/{name}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable(required = false) Long id,
                                                  @PathVariable(required = false) String name) {
        if(id != null && facultyService.getFaculty(id) != null) {
            return ResponseEntity.ok(facultyService.getFaculty(id));
        }
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByName(name));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty editFaculty(@PathVariable Long id,
                               @RequestBody Faculty faculty) {
        return facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty deleteFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("/all")
    public Collection<Faculty> getAll() {
        return facultyService.getAll();
    }

    @GetMapping("/filtered")
    public Collection<Faculty> getAllByColor(@RequestParam String color) {
        return facultyService.getFacultiesOfColor(color);
    }

    @GetMapping("/get-by-student")
    public Faculty getFacultyByStudent(@RequestParam Long studentId) {
        return facultyService.getFacultyByStudentId(studentId);
    }
}

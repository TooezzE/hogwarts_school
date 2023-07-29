package ru.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.services.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping()
    public ResponseEntity<Faculty> getFacultyInfo(@RequestParam(required = false) Long id,
                                  @RequestParam(required = false) String name) {
        Faculty faculty = null;
        if(id != null) {
            faculty = facultyService.getFaculty(id);
            return ResponseEntity.ok(faculty);
        }
        if(name != null && !name.isBlank()) {
            faculty = facultyService.getFacultyByName(name);
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filtered/{color}")
    public Collection<Faculty> getAllByColor(@PathVariable String color) {
        return facultyService.getFacultiesOfColor(color);
    }
}

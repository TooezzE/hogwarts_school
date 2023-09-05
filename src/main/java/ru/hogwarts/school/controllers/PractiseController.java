package ru.hogwarts.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.services.StudentService;

import java.util.stream.Stream;

@RestController
@RequestMapping("/practise")
public class PractiseController {

    @Autowired
    private StudentService service;

    @GetMapping("/stream")
    public int getSum() {
        return Stream.iterate(1, i -> i+1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
    }

    @GetMapping("students-threads")
    public String printStudentsNames() {
        service.printNamesInConsole();
        return "Check console";
    }

    @GetMapping("students-threads-sync")
    public String printStudentsNamesSync() {
        service.printNamesInConsoleSynhronized();
        return "Check console";
    }
}

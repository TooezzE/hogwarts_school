package ru.hogwarts.school.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/practise")
public class PractiseController {

    @GetMapping("/stream")
    public int getSum() {
        return Stream.iterate(1, i -> i+1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
    }
}

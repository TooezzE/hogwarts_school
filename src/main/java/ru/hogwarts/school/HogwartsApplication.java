package ru.hogwarts.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class HogwartsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HogwartsApplication.class, args);
    }

}

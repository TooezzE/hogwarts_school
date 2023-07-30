package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);

    Collection<Student> getStudentsByAgeBetween(Integer min, Integer max);
}

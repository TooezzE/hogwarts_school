package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);

    List<Student> getStudentsByAgeBetween(Integer min, Integer max);

    @Query(value = "SELECT count(*) FROM students", nativeQuery = true)
    Integer getAllStudentsCount();

    @Query(value = "SELECT avg(age) FROM students", nativeQuery = true)
    Integer getStudentsAvgAge();

    @Query(value = "SELECT * FROM students ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();
}

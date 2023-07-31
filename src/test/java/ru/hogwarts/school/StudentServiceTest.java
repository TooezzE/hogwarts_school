package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.exceptions.AgeLessThanZeroException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentsRepository;
import ru.hogwarts.school.services.StudentService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentsRepository studentsRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private StudentService studentService;

    Map<Long, Student> students = new HashMap<>();


    @BeforeEach
    public void setUp() {
        Faculty faculty1 = new Faculty(1L, "aaa", "green");
        Faculty faculty2 = new Faculty(2L, "bbb", "red");
        Faculty faculty3 = new Faculty(3L, "ccc", "blue");
        students.put(1L, new Student(0L, "Eva", 21));
        students.put(2L, new Student(0L, "Andrei", 20));
        students.put(3L, new Student(0L, "Egor", 21));
        students.put(4L, new Student(0L, "Vlad", 30));
        students.put(5L, new Student(0L, "Marina", 20));
        students.put(6L, new Student(0L, "OLeg", 21));

        Student student1 = new Student(7L, "Nikolai", 20);
        Student student2 = new Student(8L, "Dima", 20);

        Mockito.when(studentsRepository.save(student1)).thenReturn(students.put(7L, student1));
        Mockito.when(studentsRepository.save(student2)).thenReturn(students.put(8L, student2));
        Mockito.when(studentsRepository.findAll()).thenReturn(new ArrayList<>(students.values()));
        Mockito.when(studentsRepository.findById(2L)).
                thenReturn(Optional.ofNullable(students.values().stream().filter(s -> s.getId() == 2).findAny()
                        .orElseThrow(StudentNotFoundException::new)));
        Mockito.when(studentsRepository.findAllByAge(20))
                .thenReturn(students.values().stream().filter(s -> s.getAge() == 20).toList());
        Mockito.when(studentsRepository.getStudentsByAgeBetween(25, 40))
                .thenReturn(students.values().stream().filter(s -> s.getAge() > 25 && s.getAge() < 40).toList());

    }
    @Test
    public void creatingStudent() {
        Student expected1 = new Student(7L, "Nikolai", 20);
        Student expected2 = new Student(8L, "Dima", 20);

        assertEquals(expected1, studentService.createStudent(expected1));
        assertEquals(expected2, studentService.createStudent(expected2));
    }

    @Test
    public void getStudentById() {
        Student expected = new Student(2L, "Andrei", 20);

        assertEquals(expected, studentService.getStudent(2L));
    }

    @Test
    public void editingStudent() {
        Student expected = new Student(2L, "Andrei", 20);

        assertEquals(expected, studentService.editStudent(2L, expected));
    }

    @Test
    public void deleteStudent() {
        Student expected = new Student(2L, "Andrei", 20);

        assertEquals(expected, studentService.deleteStudent(2L));
    }


    @Test
    public void getStudentsOfAge() {
        List<Student> expected = List.of(new Student(2L, "Andrei", 20),
                new Student(5L, "Marina", 20));
        assertEquals(expected, studentService.getStudentsOfAge(20));
    }

    @Test
    public void getStudentsOfAgeThrowsExceptionIfGivenAgeIsLessThanZero() {
        assertThrows(AgeLessThanZeroException.class, () -> studentService.getStudentsOfAge(-9));
    }
}

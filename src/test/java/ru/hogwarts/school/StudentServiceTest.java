package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.exceptions.AgeLessThanZeroException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentsRepository;
import ru.hogwarts.school.services.StudentService;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentsRepository studentsRepository;
    @InjectMocks
    private StudentService studentService;

    Map<Long, Student> students = new HashMap<>();


    @BeforeEach
    public void setUp() {
        Faculty faculty1 = new Faculty(1L, "Griffindor", "Green");
        Faculty faculty2 = new Faculty(2L, "Slizerin", "Red");
        Faculty faculty3 = new Faculty(3L, "Cogtevran", "Black");
        students.put(1L, new Student(0L, "Eva", 23, faculty1));
        students.put(2L, new Student(0L, "Andrei", 20, faculty2));
        students.put(3L, new Student(0L, "Egor", 21, faculty3));
        students.put(4L, new Student(0L, "Vlad", 30, faculty1));
        students.put(5L, new Student(0L, "Marina", 20, faculty1));
        students.put(6L, new Student(0L, "OLeg", 21, faculty2));

        Student student1 = new Student(7L, "Nikolai", 20, faculty1);
        Student student2 = new Student(8L, "Dima", 20, faculty2);
        Optional<Student> student3 = Optional.of(new Student(3L, "Egor", 21, faculty3));

        Mockito.when(studentsRepository.findAll()).thenReturn(students.values().stream().toList());
        Mockito.when(studentsRepository.save(student1)).thenReturn(student1);
        Mockito.when(studentsRepository.save(student2)).thenReturn(student2);
        Mockito.when(studentsRepository.findById(3L)).thenReturn(student3);
        Mockito.when(studentsRepository.findAllByAge(20))
                .thenReturn(students.values().stream().filter(s -> s.getAge() == 20).collect(Collectors.toList()));

    }
    @Test
    public void creatingStudent() {
        Faculty faculty1 = new Faculty(1L, "Griffindor", "Green");
        Faculty faculty2 = new Faculty(2L, "Slizerin", "Red");
        Student student1 = new Student(7L, "Nikolai", 20, faculty1);
        Student student2 = new Student(8L, "Dima", 20, faculty2);

        assertEquals(student1, studentService.createStudent(student1));
        assertEquals(student2, studentService.createStudent(student2));
    }

    @Test
    public void getStudentById() {
        Faculty faculty1 = new Faculty(1L, "Griffindor", "Green");
        Faculty faculty2 = new Faculty(2L, "Slizerin", "Red");
        Optional<Student> student1 = Optional.of(new Student(4L, "Vlad", 30, faculty1));
        Optional<Student> student3 = Optional.of(new Student(6L, "Oleg", 21, faculty2));

        assertEquals(student1, studentService.getStudent(4));
        assertEquals(student3, studentService.getStudent(6));
    }

    @Test
    public void editingStudent() {
        Faculty faculty1 = new Faculty(1L, "Griffindor", "Green");
        Optional<Student> expected = Optional.of(new Student(7L, "Nikolai", 20, faculty1));
        studentService.editStudent(new Student(2L, "Nikolai", 40, faculty1));

        assertEquals(expected, studentService.getStudent(2L));
    }

    @Test
    public void deleteStudent() {
        studentService.deleteStudent(1);
        Optional<Student> actual = studentService.getStudent(1);
        assertNull(actual);
    }


    @Test
    public void getStudentsOfAge() {
        Faculty faculty1 = new Faculty(1L, "Griffindor", "Green");
        Faculty faculty2 = new Faculty(2L, "Slizerin", "Red");
        Collection<Student> expected = new ArrayList<>();
        Student student1 = new Student(5L, "Marina", 20, faculty1);
        Student student2 = new Student(2L, "Andrei", 20, faculty2);
        expected.add(student1);
        expected.add(student2);

        assertEquals(expected, studentService.getStudentsOfAge(20));
    }

    @Test
    public void getStudentsOfAgeThrowsExceptionIfGivenAgeIsLessThanZero() {
        assertThrows(AgeLessThanZeroException.class, () -> studentService.getStudentsOfAge(-9));
    }
}

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
        students.put(6L, new Student(0L, "OLeg", 21, faculty3));

        Student student1 = new Student(7L, "Nikolai", 20, faculty1);
        Student student2 = new Student(8L, "Dima", 20, faculty2);

        Mockito.when(studentsRepository.findAll()).thenReturn(students.values().stream().toList());
        Mockito.when(studentsRepository.save(student1)).thenReturn(student1);
        Mockito.when(studentsRepository.save(student2)).thenReturn(student2);
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
        Student student1 = new Student(0L, "Vlad", 20, faculty1);
        Student student3 = new Student(2L, "Igor", 23, faculty2);

        assertEquals(student1, studentService.getStudent(0));
        assertEquals(student3, studentService.getStudent(2));
    }

    @Test
    public void editingStudent() {
        Faculty faculty1 = new Faculty(1L, "Griffindor", "Green");
        Student expected = new Student(2L, "Nikita", 40, faculty1);
        studentService.editStudent(new Student(2L, "Nikita", 40, faculty1));

        assertEquals(expected, studentService.getStudent(2L));
    }

    @Test
    public void deleteStudent() {
        Student actual = studentService.getStudent(1).get();
        studentService.deleteStudent(1);
        assertNull(actual);
    }


    @Test
    public void getStudentsOfAge() {
        Faculty faculty1 = new Faculty(1L, "Griffindor", "Green");
        Faculty faculty2 = new Faculty(2L, "Slizerin", "Red");
        Collection<Student> expected = new ArrayList<>();
        Student student1 = new Student(0L, "Vlad", 20, faculty1);
        Student student2 = new Student(1L, "Oleg", 20, faculty1);
        Student student4 = new Student(3L, "Nikolai", 20, faculty2);
        expected.add(student1);
        expected.add(student2);
        expected.add(student4);

        assertEquals(expected, studentService.getStudentsOfAge(20));
    }

    @Test
    public void getStudentsOfAgeThrowsExceptionIfGivenAgeIsLessThanZero() {
        assertThrows(AgeLessThanZeroException.class, () -> studentService.getStudentsOfAge(-9));
    }
}

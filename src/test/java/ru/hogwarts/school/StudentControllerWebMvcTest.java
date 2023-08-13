package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controllers.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentsRepository;
import ru.hogwarts.school.services.AvatarService;
import ru.hogwarts.school.services.FacultyService;
import ru.hogwarts.school.services.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentsRepository studentsRepository;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @SpyBean
    private StudentService studentService;
    @SpyBean
    private FacultyService facultyService;
    @SpyBean
    private AvatarService avatarService;
    @InjectMocks
    private StudentController studentController;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void createStudentTest() throws Exception {
        final String name = "Oleg";
        final int age = 23;
        final long id = 1;


        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age",  age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentsRepository.save(any(Student.class))).thenReturn(student);
        when(studentsRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/student")
                .content(studentObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void getStudentTest() throws Exception {
        final String name = "Oleg";
        final int age = 23;
        final long id = 1;


        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age",  age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentsRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void editStudentTest() throws Exception {
        final String name = "Oleg";
        final int age = 23;
        final long id = 1;


        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age",  age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentsRepository.save(any(Student.class))).thenReturn(student);
        when(studentsRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/" + id)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        final String name = "Oleg";
        final int age = 23;
        final long id = 1;


        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age",  age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentsRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void getAllStudentsTest() throws Exception {
        final String name = "Oleg", name2 = "Vlad", name3 = "Egor";
        final int age = 23, age2 = 30, age3 = 25;
        final long id = 1, id2 = 2, id3 = 3;


        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age",  age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        JSONObject studentObject2 = new JSONObject();
        studentObject2.put("name", name2);
        studentObject2.put("age",  age2);

        Student student2 = new Student();
        student2.setId(id2);
        student2.setName(name2);
        student2.setAge(age2);

        JSONObject studentObject3 = new JSONObject();
        studentObject3.put("name", name3);
        studentObject3.put("age",  age3);

        Student student3 = new Student();
        student3.setId(id3);
        student3.setName(name3);
        student3.setAge(age3);

        List<Student> students = Arrays.asList(student, student2, student3);

        when(studentsRepository.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/all")
                        .content(studentObject.toString())
                        .content(studentObject2.toString())
                        .content(studentObject3.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(students)));
    }

    @Test
    public void getAllStudentsOfAgeTest() throws Exception {
        final String name = "Oleg", name2 = "Vlad", name3 = "Egor";
        final int age = 23, age2 = 30, age3 = 23;
        final long id = 1, id2 = 2, id3 = 3;


        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age",  age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        JSONObject studentObject2 = new JSONObject();
        studentObject2.put("id", id2);
        studentObject2.put("name", name2);
        studentObject2.put("age",  age2);

        Student student2 = new Student();
        student2.setId(id2);
        student2.setName(name2);
        student2.setAge(age2);

        JSONObject studentObject3 = new JSONObject();
        studentObject3.put("id", id3);
        studentObject3.put("name", name3);
        studentObject3.put("age",  age3);

        Student student3 = new Student();
        student3.setId(id3);
        student3.setName(name3);
        student3.setAge(age3);

        List<Student> students13 = Arrays.asList(student, student3);

        when(studentsRepository.findAllByAge(23)).thenReturn(students13);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/by-age/" + age)
                        .content(studentObject.toString())
                        .content(studentObject3.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(students13)));

    }

    @Test
    public void getStudentsOfAgeBetweenTest() throws Exception {
        final String name2 = "Vlad", name3 = "Egor";
        final int age2 = 23, age3 = 29;
        final long id2 = 2, id3 = 3;

        Student student2 = new Student();
        student2.setId(id2);
        student2.setName(name2);
        student2.setAge(age2);

        Student student3 = new Student();
        student3.setId(id3);
        student3.setName(name3);
        student3.setAge(age3);

        List<Student> students23 = Arrays.asList(student2, student3);

        when(studentsRepository.getStudentsByAgeBetween(22, 30)).thenReturn(students23);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age-between?min=22&max=30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[1].id").value(3));
    }

    @Test
    public void getStudentsOfFacultyTest() throws Exception {
        final String name = "Oleg", name2 = "Vlad";
        final int age = 21, age2 = 29;
        final long id = 1, id2 = 2;

        final Faculty faculty = new Faculty(1L, "aaa", "red");

        List<Student> facultyStudents = Arrays.asList(
                new Student(id, name, age),
                new Student(id2, name2, age2)
        );

        faculty.setStudents(facultyStudents);

        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/of-faculty/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Oleg"))
                .andExpect(jsonPath("$[1].name").value("Vlad"));
    }
}

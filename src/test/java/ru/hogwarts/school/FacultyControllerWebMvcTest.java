package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controllers.FacultyController;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private StudentsRepository studentsRepository;
    @SpyBean
    private FacultyService facultyService;
    @SpyBean
    private AvatarService avatarService;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private FacultyController facultyController;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void createFacultyTest() throws Exception {
        final String name = "aaa";
        final String color = "red";
        final long id = 1;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyTest() throws Exception {
        final String name = "aaa";
        final String color = "red";
        final long id = 1;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id + "/" + name)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void editFacultyTest() throws Exception {
        final String name = "aaa";
        final String color = "red";
        final long id = 1;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/" + id)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        final String name = "aaa";
        final String color = "red";
        final long id = 1;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getAllFacultiesTest() throws Exception {
        final String name = "aaa", name2 = "bbb", name3 = "ccc";
        final String color = "red", color2 = "green", color3 = "blue";
        final long id = 1, id2 = 2, id3 = 3;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        JSONObject facultyObject2 = new JSONObject();
        facultyObject2.put("name", name2);
        facultyObject2.put("color", color2);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color2);

        JSONObject facultyObject3 = new JSONObject();
        facultyObject3.put("name", name3);
        facultyObject3.put("color", color3);

        Faculty faculty3 = new Faculty();
        faculty3.setId(id3);
        faculty3.setName(name3);
        faculty3.setColor(color3);

        List<Faculty> faculties = Arrays.asList(faculty3, faculty2, faculty3);

        when(facultyRepository.findAll()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/all")
                        .content(facultyObject3.toString())
                        .content(facultyObject2.toString())
                        .content(facultyObject3.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(faculties)));
    }

    @Test
    public void getAllFacultiesByColorTest() throws Exception {
        final String name = "aaa", name2 = "bbb", name3 = "ccc";
        final String color = "red", color2 = "red", color3 = "blue";
        final long id = 1, id2 = 2, id3 = 3;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        JSONObject facultyObject2 = new JSONObject();
        facultyObject2.put("name", name2);
        facultyObject2.put("color", color2);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color2);

        JSONObject facultyObject3 = new JSONObject();
        facultyObject3.put("name", name3);
        facultyObject3.put("color", color3);

        Faculty faculty3 = new Faculty();
        faculty3.setId(id3);
        faculty3.setName(name3);
        faculty3.setColor(color3);

        List<Faculty> faculties12 = Arrays.asList(faculty, faculty2);

        when(facultyRepository.findAllByColorIgnoreCase("red")).thenReturn(faculties12);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filtered/" + color)
                        .content(facultyObject.toString())
                        .content(facultyObject3.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(faculties12)));

    }

    @Test
    public void getFacultyByStudentTest() throws Exception {
        final String name = "aaa", name2 = "bbb", name3 = "ccc";
        final String color = "red", color2 = "green", color3 = "blue";
        final long id = 1, id2 = 2, id3 = 3;

        final Student student = new Student(1L, "Oleg", 23);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);
        facultyObject.put("id", id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        JSONObject facultyObject2 = new JSONObject();
        facultyObject2.put("name", name2);
        facultyObject2.put("color", color2);
        facultyObject.put("id", id2);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color2);

        JSONObject facultyObject3 = new JSONObject();
        facultyObject3.put("name", name3);
        facultyObject3.put("color", color3);
        facultyObject.put("id", id3);

        Faculty faculty3 = new Faculty();
        faculty3.setId(id3);
        faculty3.setName(name3);
        faculty3.setColor(color3);

        student.setFaculty(faculty);

        when(facultyRepository.findFacultyByStudentId(1L)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filtered/" + color)
                        .content(facultyObject.toString())
                        .content(facultyObject3.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

    }
}


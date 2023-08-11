package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controllers.FacultyController;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(facultyController).isNotNull();
    }

    @Test
    public void getAllFacultiesTest() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/all", String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyByIdTest() throws Exception {
        long id = 1;
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/" + id, String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyByName() throws Exception {
        String name = "aaa";
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/" + name, String.class))
                .isNotNull();
    }

    @Test
    public void createFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("eee");
        faculty.setColor("green");

        assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void editFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("ooo");
        faculty.setColor("brown");

        assertThat(this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/faculty/" + faculty.getId(), faculty, String.class))
                .isNotNull();
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("ppp");
        faculty.setColor("white");
        faculty.setId(10L);

        Faculty actual = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/", faculty, Faculty.class);
        assertThat(actual).isNotNull();

        long id = actual.getId();
        this.testRestTemplate.delete("http://localhost:" + port + "/faculty/" + id);
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/" + id, String.class))
                .contains("\"status\":405");
    }

    @Test
    public void getAllFacultiesByColorTest() throws Exception {
        String color = "blue";
        assertThat(this.testRestTemplate.getForObject(
                "http://localhost:" + port + "/faculty/filtered?color=" + color, String.class))
                .containsIgnoringCase("\"color\":\"blue\"");
    }

    @Test
    public void getFacultyByStudentTest() throws Exception {
        long studentId = 1;
        assertThat(this.testRestTemplate.getForObject(
                "http://localhost:" + port + "/faculty/get-by-student?id=" + studentId, String.class))
                .isNotNull();
    }
}


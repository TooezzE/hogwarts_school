package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controllers.StudentController;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void getAllStudentsTest() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/all", String.class))
                .isNotNull();
    }

    @Test
    public void getStudentByIdTest() throws Exception {
        long id = 1;
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/" + id, String.class))
                .isNotNull();
    }

    @Test
    public void createStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Oleg");
        student.setAge(30);

        assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void editStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Vlad");
        student.setAge(31);

        assertThat(this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student/" + student.getId(), student, String.class))
                .isNotNull();
    }

    @Test
    public void deleteStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Fedor");
        student.setAge(23);
        student.setId(10L);

        assertThat(this.testRestTemplate.postForObject("http://localhost:" + port, student, String.class))
                .isNotNull();
        long id = student.getId();
        this.testRestTemplate.delete("http://localhost:" + port + "/student/" + id);
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/" + id, String.class))
                .isNull();
    }

    @Test
    public void getAllStudentsOfAgeTest() throws Exception {
        int age = 29;
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/by-age/" + age, String.class))
                .containsIgnoringCase("\"age\":29");
    }

    @Test
    public void getStudentsOfAgeBetween() throws Exception {
        int min = 25;
        int max = 30;
        assertThat(this.testRestTemplate.getForObject(
                "http://localhost:" + port + "/student/age-between?min=" + min + "&max=" + max, String.class))
                .containsAnyOf("\"age\":25", "\"age\":26", "\"age\":27", "\"age\":28", "\"age\":29", "\"age\":30");
    }

    @Test
    public void getStudentsOfFacultyTest() throws Exception {
        long facultyId = 1;
        assertThat(this.testRestTemplate.getForObject(
                "http://localhost:" + port + "/student/of-faculty?facultyId=" + facultyId, String.class))
                .containsIgnoringCase("faculty\":{\"id\":1,");
    }

}

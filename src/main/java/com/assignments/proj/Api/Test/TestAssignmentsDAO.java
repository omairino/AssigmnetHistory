package com.assignments.proj.Api.Test;

import com.assignments.proj.Api.dao.AssignmentsDAO;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAssignmentsDAO {
    @Autowired
    private AssignmentsDAO assignmentsDAO;
    @LocalServerPort

    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void test_get_all_assignment() throws SQLException {
        assertEquals(assignmentsDAO.getAllItems().size(), 6);
    }

    @Test
    public void test_limit_assignment_number() throws SQLException {
        assertEquals(assignmentsDAO.getAssignmentsByUserID(1, 1, 2).size(), 2);
    }

    @Test
    public void test_number_of_page() throws SQLException {
        JSONObject numOfPage = new JSONObject();
        numOfPage.put("numberOfPage", 6);
        assertEquals(assignmentsDAO.numberOfPages(1, 1), numOfPage);
    }

    @Test
    public void test_get_json() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + port + "/assignments?employeeId=1&pageNumber=1&limit=10";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(true, result.getBody().contains("assignmentName"));
    }


}

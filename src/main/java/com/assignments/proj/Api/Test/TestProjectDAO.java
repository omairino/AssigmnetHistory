//package com.assignments.proj.Api.Test;
//
//
//import com.assignments.proj.Api.dao.AssignmentsDAO;
//import com.assignments.proj.Api.dao.ProjectsDAO;
//import com.sun.corba.se.spi.activation.EndPointInfo;
//import io.restassured.RestAssured;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.sql.SQLException;
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TestProjectDAO {
//    @Autowired
//    private ProjectsDAO projectsDAO;
//    @LocalServerPort
//    private int port;
//
//    @Before
//    public void setUp() throws Exception {
//        RestAssured.port = port;
//    }
//
//
//    @Test
//    public void test_get_all_assignment() throws SQLException {
//        assertEquals(projectsDAO.getAllItems().size(), 4);
//    }
//
//    @Test
//    public void test_get_json() throws URISyntaxException {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        final String baseUrl = "http://localhost:" + port + "/projects";
//        URI uri = new URI(baseUrl);
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//
//        //Verify request succeed
//        assertEquals(200, result.getStatusCodeValue());
//        assertEquals(true, result.getBody().contains("projectName"));
//    }
//
//
//}

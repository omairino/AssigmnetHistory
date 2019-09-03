package com.assignments.proj.Api.Test;


import com.assignments.proj.Api.dao.AssignmentsDAO;
import com.assignments.proj.Api.dao.EmployeeFakeDAO;
import io.restassured.RestAssured;
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
public class testSearchAssignments {

    @Autowired
    private EmployeeFakeDAO employeeDao;
    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void test_get_all_assignment()  {
        assertEquals(employeeDao.getAllemployees().size(), 5);
    }
    @Test
    public void test_get_by_id()  {
        assertEquals(employeeDao.findByID(1).getId(), 1);
    }

    @Test
    public void test_get_by_name()  {
        assertEquals(employeeDao.findByEmployeeName("edwan").size(), 1);
    }

    @Test
    public void test_get_by_skillname()  {
        assertEquals(employeeDao.findBySkillName("java").size(), 3);
    }

    @Test
    public void test_get_by_id_and_employeename()  {
        assertEquals(employeeDao.findByIDANDEmployeeName(1,"amer").size(), 2);
    }

    @Test
    public void test_get_by_skill_and_employeename()  {
        assertEquals(employeeDao.findBySkillNameANDEmployeName("edwan","java").size(), 3);
    }

    @Test
    public void test_get_json() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + port + "/employees";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(true, result.getBody().contains("name"));
    }


}

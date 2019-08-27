package com.assignments.proj.Api.controller;

import java.util.Arrays;
import java.util.List;

import com.assignments.proj.Api.dao.AssignmentsFakeDAO;
import com.assignments.proj.Api.dao.ProjectFakeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;;

import com.assignments.proj.Api.model.Assignment;

@RestController
@RequestMapping(path = "/assignments")
public class AssignmentsController {

    @Autowired
    private AssignmentsFakeDAO assignmentsDao;
    @Autowired
    private ProjectFakeDAO p;


    @GetMapping("")
    public @ResponseBody
    List<JSONObject> getAssignmentsHistoryForEmployee(@RequestParam int empid, @RequestParam int pageNumber, @RequestParam int numberOfAssignments) {
        /**
         *  should move data processing to appropriate DAOs and only
         *  the controllers return the responses
         */
        List<Assignment> assignments;
        if (numberOfAssignments != 0) {
            assignments = assignmentsDao.getAssignmentsByUserID(empid, pageNumber, numberOfAssignments);
        } else {
            assignments = assignmentsDao.getAssignmentsByUserID(empid, pageNumber, 10);
        }

        JSONObject result = new JSONObject();
        JSONObject message = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        if (assignments.size() > 0) {
            message.put("responseData", "True");
            message.put("msg", "success");

            for (assignmentsDao as : assignments) {
                result.put("id", as.getId());
                result.put("name", as.getName());
                result.put("startDATE", as.getStartDate());
                result.put("endDATE", as.getEndDate());
                result.put("projectName", p.find(as.getProjectID()).getName());
                result.put("status", as.getStatus());
                result.put("requestedBy", as.getRequestedBy());
                json.add(result);
                result = new JSONObject();
            }
            message.put("item", json);

        } else {
            message.put("responseData", "True");
            message.put("msg", "No Item");
            message.put("item", json);

        }
        return Arrays.asList(message);
    }


}

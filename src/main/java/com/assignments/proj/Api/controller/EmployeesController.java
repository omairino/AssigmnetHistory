package com.assignments.proj.Api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.proj.Api.dao.EmployeeFakeDAO;
import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.Employee;
import com.assignments.proj.Api.model.Skill;


@RestController
@RequestMapping(path = "/employees")
public class EmployeesController {
	
	@Autowired
	private EmployeeFakeDAO employeeDao;
	
	@GetMapping("")
	public @ResponseBody List<JSONObject> all(){
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        List<Employee> employees;
        
        
        employees=employeeDao.getAllemployees();
        
        for(Employee emp: employees) {
        	result.put("id", emp.getId());
        	result.put("name", emp.getName());
        	result.put("password", emp.getPassword());
        	result.put("managerID", emp.getManagerID());
        	result.put("email", emp.getEmail());
        	result.put("phone", emp.getPhone());
        	//result.put("skill", emp.getSkills());
        	json.add(result);
        	result=new JSONObject();
        	
        	}
        
		 return json;
	}
/////////////////////////////////////////////////////////////////////////	
	@GetMapping("/id/{id}")
	public @ResponseBody JSONObject byID(@PathVariable int id) {
		
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        Employee employee;
        
        
        employee=employeeDao.findByID(id);
    	result.put("id", employee.getId());
    	result.put("name", employee.getName());
    	result.put("password", employee.getPassword());
    	result.put("managerID", employee.getManagerID());
    	result.put("email", employee.getEmail());
    	result.put("phone", employee.getPhone());
    	//result.put("skill", employee.getSkills());
    	json.add(result);
    	
    
    	return result;
        

	}
	

/////////////////////////////////////////////////////////////////////////////////////	
	@GetMapping("/name/{name}")
	public @ResponseBody List<JSONObject> byName(@PathVariable String name) {
		
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        List<Employee> employees;
        
        
        employees=employeeDao.findByEmployeeName(name);
        
        for(Employee emp: employees) {
        	result.put("id", emp.getId());
        	result.put("name", emp.getName());
        	result.put("password", emp.getPassword());
        	result.put("managerID", emp.getManagerID());
        	result.put("email", emp.getEmail());
        	result.put("phone", emp.getPhone());
        	//result.put("skill", emp.getSkills());
        	json.add(result);
        	result=new JSONObject();
        	
        	}
        
		 return json;
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////	
	@GetMapping("/skill/{skillname}")
	public @ResponseBody List<JSONObject> bySkill(@PathVariable String  skillname) {
		
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        List<Employee> employees;
        
        
        employees=employeeDao.findBySkillName(skillname);
        
        for(Employee emp: employees) {
        	result.put("id", emp.getId());
        	result.put("name", emp.getName());
        	result.put("password", emp.getPassword());
        	result.put("managerID", emp.getManagerID());
        	result.put("email", emp.getEmail());
        	result.put("phone", emp.getPhone());
        	//result.put("skill", emp.getSkills());
        	json.add(result);
        	result=new JSONObject();
        	
        	}
        
		 return json;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/IdANDEmployeeName")
	public @ResponseBody List<JSONObject> byIdAndEmployeeName(@RequestParam Integer id,@RequestParam String  EmployeeName) {
		
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        List<Employee> employees=null;
        
       
        employees=employeeDao.findByIDANDEmployeeName(id,EmployeeName);
        
        for(Employee emp: employees) {
        	result.put("id", emp.getId());
        	result.put("name", emp.getName());
        	result.put("password", emp.getPassword());
        	result.put("managerID", emp.getManagerID());
        	result.put("email", emp.getEmail());
        	result.put("phone", emp.getPhone());
        	//result.put("skill", emp.getSkills());
        	json.add(result);
        	result=new JSONObject();
        	
        	}
        
		 return json;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/IdANDSkillName")
	public @ResponseBody List<JSONObject> byIdANDSkillName(@RequestParam Integer id,@RequestParam String Skillname) {
		
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        List<Employee> employees=null;
        
       
        employees=employeeDao.findByIDANDSkillName(id,Skillname);
        
        for(Employee emp: employees) {
        	result.put("id", emp.getId());
        	result.put("name", emp.getName());
        	result.put("password", emp.getPassword());
        	result.put("managerID", emp.getManagerID());
        	result.put("email", emp.getEmail());
        	result.put("phone", emp.getPhone());
        	//result.put("skill", emp.getSkills());
        	json.add(result);
        	result=new JSONObject();
        	
        	}
        
		 return json;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/SkillNameANDEmployeName")
	public @ResponseBody List<JSONObject> byAll(@RequestParam String  EmployeeName,@RequestParam String Skillname) {
		
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        List<Employee> employees=null;
        
       
        employees=employeeDao.findBySkillNameANDEmployeName(EmployeeName,Skillname);
        
        for(Employee emp: employees) {
        	result.put("id", emp.getId());
        	result.put("name", emp.getName());
        	result.put("password", emp.getPassword());
        	result.put("managerID", emp.getManagerID());
        	result.put("email", emp.getEmail());
        	result.put("phone", emp.getPhone());
        	//result.put("skill", emp.getSkills());
        	json.add(result);
        	result=new JSONObject();
        	
        	}
        
		 return json;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/searchAll")//localhost:8080/search/?id=1&name=tamer&type=
	public @ResponseBody List<JSONObject> byAll(@RequestParam Integer id,@RequestParam String  EmployeeName,@RequestParam String Skillname) {
		
		JSONObject result = new JSONObject();
        List<JSONObject> json = new ArrayList<JSONObject>();
        List<Employee> employees=null;
        
       
        employees=employeeDao.findByIDANDSkillNameANDEmployeName(id,EmployeeName,Skillname);
        
        for(Employee emp: employees) {
        	result.put("id", emp.getId());
        	result.put("name", emp.getName());
        	result.put("password", emp.getPassword());
        	result.put("managerID", emp.getManagerID());
        	result.put("email", emp.getEmail());
        	result.put("phone", emp.getPhone());
        	//result.put("skill", emp.getSkills());
        	json.add(result);
        	result=new JSONObject();
        	
        	}
        
		 return json;
	}
	
}

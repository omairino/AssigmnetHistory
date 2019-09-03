package com.assignments.proj.Api.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.assignments.proj.Api.model.Employee;
import com.assignments.proj.Api.model.Skill;
import com.assignments.proj.Api.model.Type;


@Service
public class EmployeeFakeDAO implements IDOemployee {
	
	Skill sk1= new Skill(11,"java",Type.TECHNICAL);
	Skill sk2= new Skill(22,"C",Type.TECHNICAL);
	Skill sk3= new Skill(33,"C++",Type.PRODUCT);
	Skill sk4= new Skill(44,"Ang",Type.PRODUCT);
	Skill sk5= new Skill(55,"andro",Type.PRODUCT);
	Skill sk6= new Skill(66,"javaScript",Type.PRODUCT);

	

	
	
	List<Skill> listSkill1 =Arrays.asList(sk1,sk2);
	List<Skill> listSkill2 =Arrays.asList(sk3);
	List<Skill> listSkill3 =Arrays.asList(sk4,sk5,sk1);
	List<Skill> listSkill4 =Arrays.asList(sk4,sk5,sk1);
	List<Skill> listSkill5 =Arrays.asList(sk4,sk5,sk6);
	
	
	private List<Employee> employees=Arrays.asList(
			new Employee(1,"edwan","asd",6,"tmer@gmail.com","0543132931",listSkill1),
			new Employee(2,"aslan","asd",6,"tmer@gmail.com","0543132932",listSkill2),
			new Employee(3,"amer","asd",6,"tmer@gmail.com","0543132933",listSkill3),
			new Employee(4,"shahar","asd",6,"tmer@gmail.com","0543132934",listSkill4),
			new Employee(5,"aaaaa","asd",6,"tmer@gmail.com","0543132935",listSkill5)
			
			);
		
	

	@Override
	public List<Employee> getAllemployees(){
		return employees;
	}
////////////////////////////////////////	
	@Override
	
	public Employee findByID(int id) {
		Employee found=null;
		
		for(Employee emp:employees) {
			if(emp.getId() == id) {
				found=emp;
			}
		}
		return found;
		}

	
//////////////////////////////////////
	
	public List <Employee> findByEmployeeName(String name) {
		
	List<Employee> found = new ArrayList<Employee>();
		
		for(Employee emp:employees) {
			if(emp.getName().compareTo(name)==0 ) {
				found.add(emp);
			}
		}
		return found;
	}
	
/////////////////////////////////////////////////////////////
	public List <Employee> findBySkillName(String name ) {
		
		List <Employee> found = new ArrayList<Employee>();
		
			
			for(Employee emp:employees) {
				for(Skill sk :emp.getSkills()) {
					if(sk.getName().compareTo(name)==0) {
						found.add(emp);
					}
				}
		
			}
			return found;
		}

////////////////////////////////////////////////////////////////////////////
	
	public List<Employee> findByIDANDEmployeeName(Integer id,String Employeename) {
		
		Employee byID=null;
		List <Employee> byName=new  ArrayList<Employee>();
		
		byID=findByID(id);
		byName=findByEmployeeName(Employeename);
		
		if(!(byName.contains(byID)) && (byID!=null )){
			byName.add(byID);
		}
		
		
		return byName;	
		
		
	}
	
////////////////////////////////////////////////////////////////////////////

public List<Employee> findByIDANDSkillName(Integer id,String SkillName) {
		
		Employee byID=null;
		List <Employee> bySkill=new  ArrayList<Employee>();
		
		byID=findByID(id);
		bySkill=findBySkillName(SkillName);
		
		if(!(bySkill.contains(byID)) && (byID!=null )){
			bySkill.add(byID);
		}
		
		return bySkill;	
	}
////////////////////////////////////////////////////////////////////////////

public List<Employee> findBySkillNameANDEmployeName(String Employeename,String SkillName)
{
	List <Employee> bySkillName=new  ArrayList<Employee>();
	List <Employee> byEmployeename=new  ArrayList<Employee>();
	bySkillName=findBySkillName(SkillName);
	byEmployeename=findByEmployeeName(Employeename);
	
	for(Employee emp:byEmployeename) {
		if(!(bySkillName.contains(emp))) {
			bySkillName.add(emp);
		}
	}
	
	return bySkillName;
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////	
public List<Employee> findByIDANDSkillNameANDEmployeName(Integer id,String Employeename,String SkillName){

	List <Employee> result1=new  ArrayList<Employee>();
	Employee byID=null;
	
	byID=findByID(id);
	result1=findBySkillNameANDEmployeName(Employeename,SkillName);
	
	
	if(!(result1.contains(byID)) && (byID!=null )){
		result1.add(byID);
	}
	
	return result1;
}

}
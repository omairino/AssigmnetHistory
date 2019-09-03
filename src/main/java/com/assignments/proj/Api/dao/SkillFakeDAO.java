package com.assignments.proj.Api.dao;

import java.util.Arrays;

import java.util.*;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.Employee;
import com.assignments.proj.Api.model.Skill;

/*@Service
public class SkillFakeDAO {
	
	private List<Skill> skills = Arrays.asList(
			new Skill(1,"Java",new Date(),10,"itperfect"),
			new Skill(1,"JavaScript",new Date(),9,"itperfect"),
			new Skill(1,"C++",new Date(),8,"itperfect"));
	
	
	public Skill findByID(int id) {
		Skill found=null;
		
		for(Skill sk:skills) {
			if(sk.getId() == id) {
				found=sk;
			}
		}
		return found;
	}
	
	
	
	public Skill findByName(String name) {
		
		Skill found=null;
			
			for(Skill sk:skills) {
				if(sk.getName().compareTo(name) == 0) {
					found=sk;
				}
			}
			return found;
		}

}*/

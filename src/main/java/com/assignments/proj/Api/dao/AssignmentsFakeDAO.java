package com.assignments.proj.Api.DAO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.assignments.proj.Api.Model.Assignment;


@Service
public class AssignmentsFakeDAO implements AssignemtsCollection<Assignment>{

	private List<Assignment> assignments = Arrays.asList(
			new Assignment(1,1, 1, "Asn 1"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(2,1, 1, "Asn 2"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(3,1, 1, "Asn 3"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(4,1, 1, "Asn 4"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(5,1, 1, "Asn 5"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(6,1, 1, "Asn 6"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(7,1, 1, "Asn 7"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(8,1, 1, "Asn 8"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(9,1, 1, "Asn 9"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(10,1, 1, "Asn 10"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(11,1, 1, "Asn 11"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(12,1, 1, "Asn 12"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(13,1, 1, "Asn 13"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(14,1, 1, "Asn 14"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(15,1, 1, "Asn 15"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(16,1, 1, "Asn 16"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(17,2, 1, "Asn 17"
					, new Date(System.currentTimeMillis()), new Date(), "Pending Approval","Tarik"),
			new Assignment(18,2, 1, "Asn 18"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(19,2, 1, "Asn 19"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(20,2, 1, "Asn 20"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(21,2, 1, "Asn 21"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new Assignment(22,2, 1, "Asn 22"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"));


	public List<Assignment> getAllItems() {
		return assignments;
	}


	public Assignment insert(Assignment item) {
		//if the assignment doesn't exist add it and return the assignment with the generated id,
		// else return null
		if (!assignments.contains(item)){
			item.setId(assignments.size());
			assignments.add(item);
			return item;
		}
		return null;
	}

	/***
	 *
	 * @param @Assignment to update
	 * @return updated assignment
	 */
	
	public Assignment update(Assignment item) {
		// look for the assignment by index, if found update it and return it
		// else return null
		Assignment temp;

		int index = assignments.indexOf(item);

		if (index >= 0){
			temp = assignments.get(index);
			Assignment.copy(temp, item);
			return temp;
		}

		return null;
	}

	
	public Assignment delete(Assignment item) {
		// if removal was successful return the deleted assignment
		// else return null
		if (assignments.remove(item))
			return item;

		return null;
	}

	
	public int numberOfPages(int id, int limit) {
		// find all the assignments for some employeee
		// devide the results found by the limit to get page number
		List<Assignment> tempAsns = assignments
				.stream()
				.filter(assignment -> assignment.getEmployeeID() == id)
				.limit(limit)
				.collect(Collectors.toList());

		return (int)Math.floor(tempAsns.size()/limit) + 1;
	}

	@Override
	public List<Assignment> getAssignmentsByUserID(int id, int currPage, int limit) {
		// filter the assignments by employee id and collect them in a list
		// if not found return null
		return Optional.of(assignments
				.stream()
				.skip(currPage*limit-1)
				.filter(asn -> asn.getEmployeeID() == id)
				.limit(limit)
				.collect(Collectors.toList()))
				.orElse(null);
	}

}

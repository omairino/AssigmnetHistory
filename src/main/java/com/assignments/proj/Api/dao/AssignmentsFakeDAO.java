package com.assignments.proj.Api.dao;

import java.util.*;
import java.util.stream.Collectors;

import com.assignments.proj.Api.model.Assignment;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class AssignmentsFakeDAO implements AssignemtsCollection<Assignment> {
//    private int limitPage = 10;

    private List<Assignment> assignments = Arrays.asList(new Assignment(1, "VODAPHONE", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(1, "Cellcom", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(2, "VODAPHONE", "AABB", new Date(), new Date(), "Done", "Amjad"),
            new Assignment(3, "Cellcom", "AACC", new Date(), new Date(), "Done", "Majd"),
            new Assignment(4, "Partner", "AAAA", new Date(), new Date(), "Done", "Omair"),
            new Assignment(5, "Partner", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(6, "Partner", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(7, "Hotmobile", "AADD", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(8, "VODAPHONE", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(9, "VODAPHONE", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(10, "Hotmobile", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(11, "VODAPHONE", "AAAA", new Date(), new Date(), "Done", "Tarik"),
            new Assignment(12, "VODAPHONE", "AAAA", new Date(), new Date(), "Done", "Tarik")
    );

    @Override
    public List<Assignment> getAllItems() {
        return assignments;
    }

    @Override
    public Assignment insert(Assignment item) {
        //if the assignment doesn't exist add it and return the assignment with the generated id,
        // else return null
        if (!assignments.contains(item)) {
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
    @Override
    public Assignment update(Assignment item) {
        // look for the assignment by index, if found update it and return it
        // else return null
        Assignment temp;

        int index = assignments.indexOf(item);

        if (index >= 0) {
            temp = assignments.get(index);
            Assignment.copy(temp, item);
            return temp;
        }

        return null;
    }

    @Override
    public Assignment delete(Assignment item) {
        // if removal was successful return the deleted assignment
        // else return null
        if (assignments.remove(item))
            return item;

        return null;
    }

    @Override
    public int numberOfPages(int limitPage) {

        // find all the AssignmentHistorys for some employeee
        // devide the results found by the limit to get page number

        //List<Assignment> tempAsns = new ArrayList<>();
		/*for (Assignment asn : assignments){
			if (asn.getEmployeeID() == id){
				tempAsns.add(asn);
			}
		}*/

        List<Assignment> tempAsns = Optional.of(assignments
                .stream()
//                .filter(Assignment -> Assignment.getEmployeeID() == id)
                .collect(Collectors.toList())).orElse(null);

        if (tempAsns.size() == 0)
            return 0;
        // if number of items dives into exact number
        // return it without any further calculation
        if (tempAsns.size() % limitPage == 0)
            return tempAsns.size() / limitPage;


        return (int) Math.floor(tempAsns.size() / limitPage) + 1;
    }

    @Override
    public List<Assignment> getAssignmentsByUserID(int id, int currPage, int limit) {
        // filter the AssignmentHistorys by employee id and collect them in a list
        // if not found return null

		/*List<Assignment> tempAsns = new ArrayList<>();

		if (currPage == 0 || (currPage-1)*limit >= assignments.size())
			return tempAsns;

		for (int i = (currPage-1)*limit; i < assignments.size(); i++){
			Assignment asn = assignments.get(i);
			if (asn.getEmployeeID() == id) {
				tempAsns.add(asn);
			}
		}*/

        return Optional.of(assignments
                .stream()
                .skip((currPage - 1) * limit)
                //.filter(asn -> asn.getEmployeeID() == id)
                .limit(limit)
                .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public List<JSONObject> jsonResult() {

        JSONObject result = new JSONObject();
        result.put("numberOfPage", this.numberOfPages(10));
        result.put("item", this.assignments);

        return Arrays.asList(result);
    }


    @Override
    public Assignment find(int id) {
        for (Assignment p : this.assignments) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

}

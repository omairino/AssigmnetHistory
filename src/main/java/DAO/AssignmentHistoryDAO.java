package DAO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Model.AssignmentHistory;
import org.springframework.stereotype.Service;

@Service
public class AssignmentHistoryDAO implements AssignemtsCollection<AssignmentHistory>{
	private List<AssignmentHistory> AssignmentHistorys = Arrays.asList(
			new AssignmentHistory(1,1, 1, "Asn 1"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(2,1, 1, "Asn 2"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(3,1, 1, "Asn 3"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(4,1, 1, "Asn 4"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(5,1, 1, "Asn 5"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(6,1, 1, "Asn 6"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(7,1, 1, "Asn 7"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(8,1, 1, "Asn 8"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(9,1, 1, "Asn 9"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(10,1, 1, "Asn 10"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(11,1, 1, "Asn 11"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(12,1, 1, "Asn 12"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(13,1, 1, "Asn 13"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(14,1, 1, "Asn 14"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(15,1, 1, "Asn 15"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(16,1, 1, "Asn 16"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(17,2, 1, "Asn 17"
					, new Date(System.currentTimeMillis()), new Date(), "Pending Approval","Tarik"),
			new AssignmentHistory(18,2, 1, "Asn 18"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(19,2, 1, "Asn 19"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(20,2, 1, "Asn 20"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(21,2, 1, "Asn 21"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"),
			new AssignmentHistory(22,2, 1, "Asn 22"
					, new Date(System.currentTimeMillis()), new Date(), "Approved","Tarik"));


	@Override
	public List<AssignmentHistory> getAllItems() {
		return AssignmentHistorys;
	}

	@Override
	public AssignmentHistory insert(AssignmentHistory item) {
		//if the AssignmentHistory doesn't exist add it and return the AssignmentHistory with the generated id,
		// else return null
		if (!AssignmentHistorys.contains(item)){
			item.setId(AssignmentHistorys.size());
			AssignmentHistorys.add(item);
			return item;
		}
		return null;
	}

	/***
	 *
	 * @param @AssignmentHistory to update
	 * @return updated AssignmentHistory
	 */
	@Override
	public AssignmentHistory update(AssignmentHistory item) {
		// look for the AssignmentHistory by index, if found update it and return it
		// else return null
		AssignmentHistory temp;

		int index = AssignmentHistorys.indexOf(item);

		if (index >= 0){
			temp = AssignmentHistorys.get(index);
			AssignmentHistory.copy(temp, item);
			return temp;
		}

		return null;
	}

	@Override
	public AssignmentHistory delete(AssignmentHistory item) {
		// if removal was successful return the deleted AssignmentHistory
		// else return null
		if (AssignmentHistorys.remove(item))
			return item;

		return null;
	}

	@Override
	public int numberOfPages(int id, int limit) {
		// find all the AssignmentHistorys for some employeee
		// devide the results found by the limit to get page number
		List<AssignmentHistory> tempAsns = AssignmentHistorys
				.stream()
				.filter(AssignmentHistory -> AssignmentHistory.getEmployeeID() == id)
				.limit(limit)
				.collect(Collectors.toList());

		return (int)Math.floor(tempAsns.size()/limit) + 1;
	}

	@Override
	public List<AssignmentHistory> getAssignmentsByUserID(int id, int currPage, int limit) {
		// filter the AssignmentHistorys by employee id and collect them in a list
		// if not found return null
		return Optional.of(AssignmentHistorys
				.stream()
				.skip(currPage*limit-1)
				.filter(asn -> asn.getEmployeeID() == id)
				.limit(limit)
				.collect(Collectors.toList()))
				.orElse(null);
	}
}

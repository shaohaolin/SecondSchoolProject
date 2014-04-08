package Lab8;

import java.util.LinkedList;
import java.util.List;



public class PatientDatabase implements PatientDatabaseInterface{
	
	private HashingDictionary<PatientRecords, PatientRecords> records; // store paitient record
	private HashingDictionary<String, List<String>> visitDates; // store the list of visitDates for each patient ID
	
	public PatientDatabase(){
		records = new HashingDictionary<PatientRecords, PatientRecords>();
		visitDates = new HashingDictionary<String, List<String>>();
	}

	@Override
	public PatientRecords add(PatientRecords record) {
		// TODO Auto-generated method stub
		PatientRecords result = records.add(record, record);
		if(result == null){
			String patientID = String.valueOf(record.getID());
			
			if(getDates(record.getID())==null){	// no list is created for this patient
			List<String> dateOfVisit = new LinkedList<String>();
			dateOfVisit.add(record.getDate());
			visitDates.add(patientID, dateOfVisit);
			}
			else {
				List<String> dateOfVisit = getDates(record.getID());
				dateOfVisit.add(record.getDate());
				visitDates.add(patientID, dateOfVisit);
			}
		}
		
		return null;
	}

	@Override
	public PatientRecords getRecord(int id, int month, int day, int year){
		// TODO Auto-generated method stub
		try {
			PatientRecords temp = new PatientRecords(id, month, day, year, null, null);
			if (temp.equals(records.getValue(temp))){
				temp = records.getValue(temp);
			}
			return temp;
			
		} catch (BadVisitDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getReason(int id, int month, int day, int year) {
		// TODO Auto-generated method stub
		String result = null;
		PatientRecords temp = getRecord(id, month, day, year);
		if(temp != null){
			result = temp.getReason();
		}
		
		return result;
	}

	@Override
	public String getTreatment(int id, int month, int day, int year) {
		// TODO Auto-generated method stub
		
		String result = null;
		PatientRecords temp = getRecord(id, month, day, year);
		if(temp != null){
			result = temp.getTreatment();
		}
		
		return result;
		
	}

	@Override
	public List<String> getDates(int id) {
		// TODO Auto-generated method stub
		return visitDates.getValue(String.valueOf(id));
	}
	

}

interface PatientDatabaseInterface {
	
	public PatientRecords add(PatientRecords record);
	public PatientRecords getRecord(int id, int month, int day, int year);
	public String getReason(int id, int month, int day, int year);
	public String getTreatment(int id, int month, int day, int year);
	public List<String> getDates(int id);
}
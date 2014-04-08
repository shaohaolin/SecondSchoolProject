package Lab8;

import java.util.List;

public class TestPatientClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatientRecords patient1;
		PatientRecords patient2;
		
		try
		{
		patient1 = new PatientRecords(101,12,15,2006,"cough", "bed rest");
		System.out.println("Patient Record created: "+ patient1);
		System.out.println("It has hash code "+patient1.hashCode());
		}
		catch (BadVisitDateException e){
			System.out.println("Creation failed "+e);
		}
		
		try{
		patient2 = new PatientRecords(101,12,17,2006,"high fever", "antibiotics");
		System.out.println("Patient Record created: "+ patient2);
		System.out.println("It has hash code "+patient2.hashCode());
		}
		catch (BadVisitDateException e){
			System.out.println("Creation failed "+e);
		}
		
		try{
		patient2 = new PatientRecords(101,17,15,2006,"cough", "bed rest");
		System.out.println("Patient Record created: "+ patient2);
		System.out.println("It has hash code "+patient2.hashCode());
		}
		catch (BadVisitDateException e){
			System.out.println("Creation failed "+e);
		}
		
		try{
			patient2 = new PatientRecords(101,12,92,2006,"high fever","antibiotics");
			System.out.println("Patient Record created: "+patient2);
			System.out.println("It has hash code "+ patient2.hashCode());
		}
		catch (BadVisitDateException e){
			System.out.println("Creation failed "+e);
		}
		
		try{
			patient2 = new PatientRecords(101,12,17,06,"high fever","antibiotics");
			System.out.println("Patient Record created: "+patient2);
			System.out.println("It has hash code "+ patient2.hashCode());
		}
		catch (BadVisitDateException e){
			System.out.println("Creation failed "+e);
		}
		
		PatientDatabase pdb = new PatientDatabase();
		PatientRecords toAdd;
		try
		{
			toAdd = new PatientRecords(101,12,14,2006,"cough", "bed rest");
			pdb.add(toAdd);
			
			toAdd = new PatientRecords(102,12,14,2006,"cold sores", "ointment");
			pdb.add(toAdd);
			
			toAdd = new PatientRecords(101,12,15,2006,"fever", "aspirin");
			pdb.add(toAdd);
		}
		catch(BadVisitDateException e)
		{
			System.out.println("Creation failed "+e);
		}
		
		PatientRecords pulled = pdb.getRecord(101, 12, 14, 2006);
		System.out.println("\nRecord for patient 101 on 12/14/2006:\n" + pulled);
		
		pulled = pdb.getRecord(101, 12, 15, 2006);
		System.out.println("\nRecord for patient 101 on 12/15/2006:\n" + pulled);
		
		pulled = pdb.getRecord(101, 12, 12, 2006);
		System.out.println("\nRecord for patient 101 on 12/12/2006:\n" + pulled + "(should be null)");
		
		String aReason = pdb.getReason(102, 12, 14, 2006);
		String aTreatment = pdb.getTreatment(102, 12, 14, 2006);
		System.out.println("\nReason and treatment for patient 102 on 12/14/2006:\n" + aReason + ":" + aTreatment);
		System.out.println();
		
		List<String>visitedOn = pdb.getDates(101);
		System.out.println("Patient 101 visited on " + visitedOn);
		
		visitedOn = pdb.getDates(102);
		System.out.println("Patient 102 visited on " + visitedOn);
		
		visitedOn = pdb.getDates(103);
		System.out.println("Patient 103 visited on " + visitedOn + "(should be null)");
		
	}

}

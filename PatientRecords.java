package Lab8;

public class PatientRecords {
	private int id;
	private int month;
	private int day;
	private int year;
	private String date;
	private String reasonForVisit;
	private String treatmentPrescribed;
	
	//constructor
	PatientRecords(){
		id = 0;
		date = null;
		reasonForVisit = null;
		treatmentPrescribed = null;
	}
	
	//constructor with id and date
	PatientRecords(int id, int month, int day, int year,String reason, String treatment) throws BadVisitDateException{
		this.id = id;
		
		if(month<1 || month >12){
			throw new BadVisitDateException("Month not in range 1-12");
		}
		this.month = month;
		
		if(day<1 || day >31){
			throw new BadVisitDateException("Day not in range 1-31");
		}
		this.day = day;
		
		if(year <1900 || year >2014){
			throw new BadVisitDateException("Year not great than 1900 and less than 2014");
		}
		this.year = year;
		this.date = String.valueOf(month)+"-"+String.valueOf(day)+"-"+String.valueOf(year);
		reasonForVisit = reason;
		treatmentPrescribed = treatment;
	}
	
	//set ID
	void setID(int num_id){
		id = num_id;
	}
	
	//get ID
	int getID() {
		return id;
	}
	
	
	//get day
	int getDay(){
		return day;
	}
	
	//get month
	int getMonth(){
		return month;
	}
	
	//get year
	int getYear(){
		return year;
	}
	
	//set date
	void setDate(String currentDate){
		date = currentDate;
	}
	
	//get date
	String getDate(){
		return date;
	}
	
	//set reasonForVisit
	void setReason(String reason){
		reasonForVisit = reason;
	}
	
	//get reason for visit
	String getReason(){
		return reasonForVisit;
	}
	
	//set Treatment Prescribed
	void setTreatment(String treatment){
		treatmentPrescribed = treatment;
	}
	
	//get treatment prescribed
	String getTreatment(){
		return treatmentPrescribed;
	}
	
	@Override
	
	public String toString(){
		return "Patient ID: "+id+" visited on "+date+".\n The reason for visiting is "+
				reasonForVisit+".\n Treatment prescribe is "+treatmentPrescribed+".";
	}
	
	@Override
	public boolean equals(Object obj){

		if(obj == null)
			return false;
		if(obj instanceof PatientRecords){
			if(((PatientRecords) obj).getID() == this.getID()){
				if(((PatientRecords) obj).getDate().equals(this.getDate()))
					return true;
			}
		}
		return false;
		
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime*result + Math.abs(id*date.hashCode());
		return result;
	}
}

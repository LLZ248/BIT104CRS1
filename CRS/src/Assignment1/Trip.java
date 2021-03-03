package Assignment1;

import java.util.Optional;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.stream.*;
import java.util.List;

/**
* 
* 
* 
* @author Lee Lin Zheng B1802130<br>
* represents the trip 
* each trip is created by a staff and can be applied by multiple volunteers
*/
public class Trip {
        /**
        * A String attribute which represents the tripID.
        */
	private String tripID;
        /**
        * A static variable which is used as counter to generate tripID
        */
        private static int tripIDCount = 1;
        /**
        * A String attribute which represents the description of the trip.
        */
	private String description;
        /**
        * A LocalDate attribute which represents the trip date.
        */
	private LocalDate tripDate;
        /**
        * A String attribute which represents the destination of the Trip.
        */
	private String location;
        /**
        * An int attribute which represents the number of volunteers required
        */
	private int numVolunteers;
        /**
        * A CrisisType enum instance attribute which represents the
        * crisis type of the Trip
        */
        private CrisisType crisisType;
        /**
        * Enum for the CrisisType
        */
        enum CrisisType{
        FLOOD,EARTHQUAKE,WILDFIRE
        }
        
        /**
        * An attribute of hashtable<k,v> to stores the Application objects.
        * The applicationID of the Application object is the <k>.
        * The Application object is the <v>.
        */
	private Hashtable<String, Application> 
                applicationHashTable;
        
        
        
	/**
         * This a the constructor method for Trip object
	 * @param description A String object 
	 * @param tripDate A LocalDate obejct
	 * @param location A String object
	 * @param numVolunteers int 
         * @param crisisType char
	 */
	public Trip(String description, LocalDate tripDate,
			String location, int numVolunteers, 
                        char crisisType) {
            
		setTripID();
		setDescription(description);
		setTripDate(tripDate);
		setLocation(location);
		setNumVolunteers(numVolunteers);
                setCrisisType(crisisType);
                applicationHashTable = new Hashtable<>();
	}
	
	/**
	 * 
	 * @return the tripID or "No Trip ID" if tripID is null
	 */
	public String getTripID() {
		return Optional.ofNullable(""+tripID)
                        .orElseGet(() -> "No Trip ID");
	}
	/**
	 * set a new tripID for the Trip object
	 */
	public void setTripID() {
		this.tripID = String.format("TR%05d", Trip.tripIDCount);
                Trip.tripIDCount ++;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Optional.ofNullable(description)
                        .orElse("no description");
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the tripDate
	 */
	public String getTripDate() {
            
            return Optional.ofNullable(tripDate)
                    .map(date ->{return ""+date;}
            ).orElse("no trip date");
            
	}
	/**
	 * @param tripDate the tripDate to set
	 */
	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return Optional.ofNullable(location)
                        .orElseGet(() -> "No Location");
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the numVolunteers
	 */
	public int getNumVolunteers() {
		return Optional.ofNullable(numVolunteers)
                        .orElseGet(()->0);
	}
	/**
	 * @param numVolunteers the numVolunteers to set
	 * if numVolunteer is less than or equal 0, 
         * then numVolunteer becomes 10
	 */
	public void setNumVolunteers(int numVolunteers) {
		numVolunteers = (numVolunteers > 0 ? numVolunteers : 10);
		this.numVolunteers = numVolunteers;
	}
        
        /**
	 * @return the getCrisisType
	 */
        public CrisisType getCrisisType() {
            return crisisType;
        }
        
        /**
         * 
         * @param crisisType valid char will be F,E,W,f,e,w
         * @return error code 0 if no error ,1 if invalid char
         */
        public int setCrisisType(char crisisType) {
            switch (crisisType){
                case 'F':case'f':
                    this.crisisType = CrisisType.FLOOD;
                    return 0;
                case 'E':case'e':
                    this.crisisType = CrisisType.EARTHQUAKE;
                    return 0;
                case 'W':case'w':
                    this.crisisType = CrisisType.WILDFIRE;
                    return 0;
                default:
                    //if other char, return error code 1
                    return 1;
            }
        }
        
        /**
         * 
         * @return A List of Application
         */
        public List<Application> getApplicationDetails() {
            return getApplicationHashTable().values().stream()
                    .collect(Collectors.toList());
        }
        
        /**
         * check is the trip has any application
         * @return true is empty false if there is any application
         */
        public boolean hasNoApplication(){
            if(getApplicationHashTable().size()==0)return true;
            return false;
        }
        
        /**
         * Check is theVolunteer already applied for this trip
         * @param theVolunteerID A String object
         * @return  true if repeated, false if not repeated
         */
        public boolean isRepeatedVolunteer(String theVolunteerID){
            if (getApplicationHashTable().values().stream().
                    anyMatch(app->app.getVolunteerID().
                            equalsIgnoreCase(theVolunteerID))){
                return true;
            }
            return false;
        }
        
        /**
         * check is the applicationHashTable full yet
         * @return false if not yet full, true if full
         */
        public boolean isApplicationHashTableFull(){
            if(getApplicationHashTable().values().stream().
                    filter(app->app.getStatus().equalsIgnoreCase("ACCEPTED"))
                    .count() < getNumVolunteers()){
                return false;
            }
            return true;
        }
        
        /**
         * 
         * @param theVolunteerID String
         * @return result of the Volunteer's application for the trip
         */
        public String getApplicationDetails(String theVolunteerID) {
            return getApplicationHashTable().values().stream()
                    .filter(app->app.getVolunteerID()
                    .equalsIgnoreCase(theVolunteerID)).findFirst()
                    .map(Object::toString).orElse("");  
        }

        /**
         * 
         * @return applicationHashTable 
         */
        public Hashtable<String, Application> getApplicationHashTable() {
            return applicationHashTable;
        }
        
        /**
         * adding application into the applicationHashTable
         * @param theVolunteerID String
         * @return error
         * 0. no error
         * 1. the table is already full
         * 2. Duplicate Volunteer
         */
        public int addNewApplication(String theVolunteerID){
            if(!isApplicationHashTableFull()){
                if(!isRepeatedVolunteer(theVolunteerID)){
                    Application app =  new Application(theVolunteerID);
                    getApplicationHashTable().put(app.getApplicationID(), app);
                    return 0;
                }else{return 2;}
            }else{
                return 1;
            }
        }
        
        /**
         * if the application hashtable is already full after approving an 
         * application, other applications in the hashtable will be set as
         * rejected
         * @param remarks String
         * @param theApplicationID String
         * @param theApplicationStatus String
         * @return true if success false if failed
         * 
         */
        public boolean changeApplicationStatusByApplicationID(
                String theApplicationID, char theApplicationStatus, 
                String remarks){
            theApplicationStatus = Character.toUpperCase(theApplicationStatus);
            if(getApplicationHashTable().containsKey(theApplicationID)){
                Application theApp = getApplicationHashTable()
                        .get(theApplicationID);
                if(theApp.getStatus().equalsIgnoreCase("ACCEPTED")
                        ||theApp.getStatus().equalsIgnoreCase("REJECTED")){
                    return false;
                }
                else{
                    if(theApplicationStatus == 'A'){
                        System.out.println("Accepted");
                        //accepted
                        if(!isApplicationHashTableFull()){
                            if(theApp.setStatus(theApplicationStatus)==0){
                                theApp.setRemarks(remarks);
                                //check is the hashtable full after approving 
                                //this applcation
                                if(isApplicationHashTableFull()){
                                    //loop the hashtable and make other NEW 
                                    //application became REJECTED
                                    Iterator it = getApplicationHashTable()
                                            .values().iterator();
                                    while(it.hasNext()){
                                        Application app = (Application)it
                                                .next();
                                        if(app.getStatus().equals("NEW")){
                                            app.setStatus('R');
                                        }
                                    }
                                }
                                return true;
                            }else{
                                return false;//invalid type
                            }
                        }else{
                            //the application table is alreay full
                            return false;
                        }
                    }
                    //rejected
                    else if(theApp.setStatus(theApplicationStatus)==0){
                        theApp.setRemarks(remarks);
                        return true;
                    }else{
                        return false;//invalid type
                    }
                }
                
            }else
                return false;   
        }
        
	@Override
	public String toString() {
            return  "\nTripID                : " + getTripID() + 
                    "\nDescription           : " + getDescription() + 
                    "\nTripDate              : " + getTripDate() + 
                    "\nLocation              : " + getLocation() + 
                    "\nCrisis Type           : " + getCrisisType()+
                    "\nNumber of Volunteers  : " + getNumVolunteers();
	}
	
	
}

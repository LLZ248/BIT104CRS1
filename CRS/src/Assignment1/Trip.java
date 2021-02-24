package Assignment1;

import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.*;
import java.util.List;

/**
* 
* 
* 
* @author B1802130
* 
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
	 * @param description
	 * @param tripDate
	 * @param location
	 * @param numVolunteers
         * @param crisisType
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

        public List<Application> getApplication() {
            return getApplicationHashTable().values().stream()
                    .collect(Collectors.toList());
        }
        
        public String getApplication(String username) {
            return getApplicationHashTable().values().stream().findFirst()
                    .filter(app->app.getVolunteer().getUsername()
                    .equalsIgnoreCase(username)).map(Object::toString).get();    
        }

        public Hashtable<String, Application> getApplicationHashTable() {
            return applicationHashTable;
        }
        
        
        //adding application into the applicationArr
        /**
         * 
         * @param theVolunteer
         * @return 
         */
        public int addNewApplication(Volunteer theVolunteer){
            if(applicationHashTable.size()<getNumVolunteers()){
                Application anApplication = new Application(theVolunteer);
                getApplicationHashTable().put(
                        anApplication.getApplicationID()
                        ,anApplication);
                return 0;
            }
            return 1;
        }
        
        /**
         * 
         * @param theApplicationID
         * @param theApplicationStatus
         * @return 
         */
        public boolean changeApplicationStatusByApplicationID(
                String theApplicationID, char theApplicationStatus){
            if(getApplicationHashTable().contains(theApplicationID)){
                getApplicationHashTable().get(theApplicationID)
                    .setStatus(theApplicationStatus);
                return true;
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

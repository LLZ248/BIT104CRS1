package Assignment1;

import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.stream.*;
import java.time.format.*;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Lee Lin Zheng B1802130
 */
public class CRS{

    //Msnanger username and password
    private String managerUsername;
    private String managerPassword;
    
    //Hash table to store Users objects (Volunteer and Staff)
    private Hashtable<String,User> CRSUsers;
    
    //Hash table to store Trip objects 
    private Hashtable<String,Trip> CRSTrips;
    
    //Hash table to record which staff record the assigned trips
    //store staff username and trip ID
    private Hashtable<String,List<String>> CRSAssignedTrips;
    
    //valid userType in the system
    enum UserType{
    MANAGER,STAFF,VOLUNTEER
    }
    
    /**
     * constructor for CRS
     * @param adminUsername String
     * @param adminPassword String
     */
    public CRS(String adminUsername, String adminPassword){
        setManagerUsername(adminUsername);
        setManagerPassword(adminPassword);
        this.CRSUsers = new Hashtable<>();
        this.CRSTrips = new Hashtable<>();
        this.CRSAssignedTrips = new Hashtable<>();
    }
    
    /**
     * getter for managerUsername
     * @return managerUsername 
     */
    public String getManagerUsername() {
        return managerUsername;
    }

    /**
     * setter for managerUsername
     * @param managerUsername String
     */
    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    /**
     * getter for managerPassword
     * @return managerPassword
     */
    public String getManagerPassword() {
        return managerPassword;
    }

    /**
     * setter for managerPassword
     * @param managerPassword String
     */
    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }
   
    /**
     * getter for CRSUsers 
     * @return CRSUsers
     */
    public Hashtable<String, User> getCRSUsers() {
        return CRSUsers;
    }
    /**
     * find a user throw error if cannot find
     * @param username the username of the user
     * @return user
     * @throws NoSuchElementException if no user with this username is found
     */
    public User findUser(String username){
        return Optional.ofNullable(getCRSUsers().get(username)).orElseThrow();
    }
    
    /**
     * check is the user a Volunteer, throws nothing if the user is Volunteer
     * @param username  the username to be checked
     * @throws RuntimeException if the user is not volunteer
     * @throws NoSuchElementException if no user with this username is found
     */
    public void isVolunteer(String username){
        if (!(findUser(username) instanceof Volunteer))
            throw new RuntimeException("The user is not volunteer");
    }
    /**
     * check is the user a Staff, throws nothing if the user is Staff
     * @param username  the username to be checked
     * @throws RuntimeException if the user is not Staff
     * @throws NoSuchElementException if no user with this username is found
     */
    public void isStaff(String username){
        if (!(findUser(username) instanceof Staff))
            throw new RuntimeException("The user is not staff");
    }

    /**
     * getter for CRSTrips
     * @return CRSTrips
     */
    public Hashtable<String, Trip> getCRSTrips() {
        return CRSTrips;
    }
    
    /**
     * add a trip into the CRSTrips.Staff who records the trip will be recorded
     * in CRSAssignedTrips
     * @param staffUsername the Staff username
     * @param theTrip Trip
     */
    public void addCRSTrip(String staffUsername, Trip theTrip){
        addKAndListVintoHashtable(getCRSAssignedTrips(),
                staffUsername,theTrip.getTripID());
        getCRSTrips().put(theTrip.getTripID(), theTrip);
    }
    

    /**
     * getter for CRSAssignedTrips
     * @return CRSAssignedTrips Hashtable
     */
    public Hashtable<String, List<String>> getCRSAssignedTrips() {
        return CRSAssignedTrips;
    }
    
    /**
     * add a Volunteer into CRS
     * @param theVolunteer the volunteer to be added
     */
    public void addVolunteer(Volunteer theVolunteer){
        getCRSUsers().put(theVolunteer.getUsername(), theVolunteer);
    }
    
    /**
     * add a staff into the CRSUsers list
     * @param theStaff the staff to be added
     */
    public void addStaff(Staff theStaff){
        getCRSUsers().put(theStaff.getUsername(),theStaff);
    }
    
    /**
     * Staff method. return a list of tripID which are responsible by the staff
     * @param staffUsername the staff username
     * @return list of tripID which are responsible by the staff
     */
    public Optional<List<String>> searchTripsforStaff(String staffUsername){
        try{
            isStaff(staffUsername);
            return Optional.ofNullable(getCRSAssignedTrips().
                    get(staffUsername));
        }catch(Exception e){
            //not staff
        }
        return null;
    }
    
    /**
     * Staff method. Display the trips and applications of the trips by the 
     * staff and manage those application.
     * @param staffUsername the staff username
     * @param appID application ID to be processed
     * @param choice a char represents the staff choice
     * @param remarks can be null if no remarks
     * @return int error code<br>
     * 0. no error<br>
     * 1.The Staff is not responsible for the trip or not existing 
     * application ID.<br>
     * 2. This application has already been processed before.<br>
     */
    public int manageApplication(String staffUsername,String appID,char choice
            , String remarks){
        Trip theTrip = null;String tripID=null; 
        boolean valid = false;
        Iterator it = getCRSAssignedTrips().get(staffUsername)
                .iterator();
        while(it.hasNext()){
            tripID = it.next().toString();
            if (CRSTrips.get(tripID).getApplicationDetails().stream()
                    .anyMatch(app -> app.getApplicationID().equals(appID)))
                valid = true;
        }
        if(valid){
            theTrip = CRSTrips.get(tripID);
        }else{
            return 1;
        }
        if(theTrip.changeApplicationStatusByApplicationID(appID,choice,
                remarks)){
            return 0;
        }else{
            return 2;
        }
    }
    
    /**
     * change the user username based on username
     * do nothing if no such user
     * @param username the username
     * @param newName the new name for the user
     * 
     */
    public void changeUsername(String username, String newName){
        findUser(username).setName(newName);
    }
    
    /**
     * change the user phone number based on username
     * @param username the username
     * @param newPhoneNum the new phone number for the user
     * 
     */
    public void changePhoneNumber(String username, String newPhoneNum){
        findUser(username).setPhone(newPhoneNum);
    }
    
    /**
     * change the user phone number based on username
     * @param username the username
     * @param newPassword the new password for the user
     * 
     */
    public void changePassword(String username,String newPassword){
        findUser(username).setPassword(newPassword);
    }
    
    /**
     * add a document for the volunteer
     * @param username username of the volunteer
     * @param theDocument the document to be added
     */
    public void addDocumentForVolunteer(String username, Document theDocument){
        try{
            isVolunteer(username);
            ((Volunteer)findUser(username)).addDocument(theDocument);
        }catch(Exception e){
            //not volunteer
        }
        
        
    } 
    
    /**
     * add volunteer into a trip
     * @param username the username of the volunteer
     * @param tripID the trip id
     * return int
     * 0 - No error<br>
     * 1 - No trip is currently available.<br>
     * 2 - The Volunteer has already applied for a trip which 
     * is on the same day<br>
     * 3 - The Trip is already full<br>
     * 4 - The Trip with the trip ID is not found<br>
     * 
     */
    public int addVolunteerIntoTrip(String username,String tripID){
        if (CRSTrips.isEmpty()){
            return 1;
        }else{
            if(username!=null||tripID!=null){
                if(CRSTrips.containsKey(tripID)){
                if(!CRSTrips.values().stream().filter(t->t.
                        isRepeatedVolunteer(username)).
                        filter(t->!t.getTripID().equalsIgnoreCase(tripID)).
                        map(Trip::getTripDate).collect(Collectors.toList()).
                        stream().allMatch(t->!t.equalsIgnoreCase(CRSTrips.
                                get(tripID).getTripDate()))){
                    return 2;
                }else{
                    switch(CRSTrips.get(tripID)
                        .addNewApplication(username)){
                    case 0:
                        return 0;
                    case 1:
                        return 3;
                    case 2:
                        return 2;
                    default:
                        break;
                    }
                    }
                }else{
                    return 4;
                }
            }
            
        }
        return 0;
    }
    
    /**
     * Volunteer method.Volunteer wants to check the status of 
     * his or her applications. The volunteer select to view the trips that 
     * they have applied for. The trip date, description and 
     * application status and remarks will be shown. 
     */
    public String viewApplicationStatus(String username){
        try{
            isVolunteer(username);
        }catch(NoSuchElementException e){
            //cannot find the user with the username
            return "invalid username";
        }catch(RuntimeException e){
            //the user is not volunteer
            return "The user is not volunteer";
        }
        
        String result = "";
        Iterator it = CRSTrips.values().stream().filter(t->t.
                isRepeatedVolunteer(username))
                .collect(Collectors.toList()).iterator(); 
        while(it.hasNext()){
            Trip trip = (Trip)it.next();
            result = result + trip +"\n";
            result = result + trip.getApplicationDetails(username)+
                    "\n----------------------------------------\n";
        }
        if(result.isBlank()){
            return "You have not applied for any trip yet.";
        }else{
            return result;
        }
    }
    
    /**
     * method to verify is the login is valid. 
     * @param uInfo A String array contains username and password
     * @return errorCode
     * 0 - valid
     * 1 - no username
     * 2 - wrong password
     * -99- admin login
     */
    public int veifyLogin(String[] uInfo){
        Optional<User> user = Optional.ofNullable(getCRSUsers().get(uInfo[0]));
        int errorCode = 
        user.isEmpty()?1:(user.get().getPassword().equals(uInfo[1])?0:2);
        //Specific login verify for CRS admin
        errorCode = (uInfo[0].equalsIgnoreCase(getManagerUsername()) && 
                uInfo[1].equals(getManagerPassword()))?-99:errorCode;
        return errorCode;
    }
    
    /**
     * Admin method to view all the trips and its applications
     * @return result in String 
     */
    public String viewTrips(){
        String result = "";
        if(getCRSTrips().isEmpty()){
            result = "No trip is created yet.";
        }else{
            Iterator it = getCRSTrips().values().iterator();
            while(it.hasNext()){
                Trip trip = (Trip)it.next();
                result = result +"Trip information:\n"+ trip.toString();
                String result2 = trip.getApplicationDetails().stream()
                        .map(Object::toString).
                        collect(Collectors.joining("\n"));
                result2 = result2.isBlank()?"\nNo application for "
                        + "this trip yet.":result2;
                result = result+
                        "\n----------------------------------\n"+"Application "
                        + "information:\n"+result2+
                        "\n----------------------------------\n";
            }
        }
        return result;
    }
    
    /**
     * Admin method to view all recorded Staff 
     * @return result in String
     */
    public String viewStaffs(){
        String result = null;
        result = CRSUsers.values().stream().filter(u->u instanceof Staff)
                .map(u->(Staff)u)
                .map(Object::toString).collect(Collectors.joining("\n"));
        result = result.isBlank()?"No staff is recorded.":result;
        return result;
    }
    
    /**
     * Admin method to view all recorded Volunteer 
     * @return result in String
     */
    public String viewVolunteers(){
        String result = null;
        result = CRSUsers.values().stream().filter(u->u instanceof Volunteer)
                .map(u->(Volunteer)u)
                .map(Object::toString).collect(Collectors.joining("\n"));
        result = result.isBlank()?"No volunteer is recorded.":result;
        return result;
    }
    /**
     * Method to check username, return true if valid, false if invalid
     * @param uName String
     * @return isValid
     */
    public boolean isValidUsername(Optional<String> uName){
        if(uName.isPresent()){
            if(getCRSUsers().containsKey(uName.get())){
                return false;//repeated key
            }else if(uName.get().equalsIgnoreCase(managerUsername)){
                return false;//same as admin username
            }
            return true;//valid key
        }
        
        return false;//null value
    }
    
    /**
     * Method to check phone number, return true if valid, false if invalid
     *@param phone String
     *check wether is the phone a valid string to be convert into int
     * @return the isValid
     */
    public boolean isValidPhone(String phone) {
        try {
            Integer.parseInt(phone);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * Method to check date, return true if valid, false if invalid
     *@param dateStr String
     *check wether is the dateStr a valid string to be convert into LocalDate
     * @return the isValid
     */
    public boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
    
    /**
     * add key and value into a hashtable. If the key
     * already exists, the add value into the List. If new key and value,
     * create a new list and add the value into the list, then put the 
     * key and list into the hashtable.
     * @param ht the hashtable
     * @param k key
     * @param v value
     */
    public static void addKAndListVintoHashtable(
        Hashtable<String,List<String>> ht, String k, String v){
        if(ht.containsKey(k)){
            ht.get(k).add(v);
        }else{
            List<String> list = new ArrayList<>();
            list.add(v);
            ht.put(k, list);
        }
    }
}


package Assignment1;
import java.util.Optional;
import java.time.LocalDate;
/**
 *
 * @author LLZ
 */
public class Application {
    private static int applicationIDCount = 1;
    private String applicationID,remarks;
    private LocalDate applicationDate;
    private Status status;
    private String volunteerID;
    
    
    enum Status{
        NEW,ACCEPTED,REJECTED
    }
    
    /**
     * 
     * @param volunteerID 
     * A volunteer username that indicates the volunteer of the application
     */
    public Application(String volunteerID) {
        setApplicationID();
        setVolunteerID(volunteerID);
        setApplicationDate(LocalDate.now());
        setStatus('N');
        setRemarks("No remark yet");
    }
    /**
     * 
     * @return 
     * return application ID of the object
     */
    public String getApplicationID() {
        return Optional.ofNullable(applicationID)
                .orElseGet(()->"No ApplicationID") ;
    }
    /**
     * generate and set applciationID for the object, also
     * increase the applicationIDCount by 1
     */
    public void setApplicationID() {
        this.applicationID = String.format("AP%05d",
                Application.applicationIDCount);
        Application.applicationIDCount++;
    }
    /**
     * 
     * @return volunteerID
     * get the volunteerID that attached to this object
     */
    public String getVolunteerID() {
        return volunteerID;
    }

    /**
     * 
     * @param volunteerID 
     * set the volunteer for this object
     */
    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }

    /**
     * 
     * @return 
     * return the applicationDate in string, return "No ApplicationDate" 
     * if null
     */
    public String getApplicationDate() {
        return Optional.ofNullable(""+applicationDate)
                .orElseGet(()->"No ApplicationDate") ;
    }

    /**
     * 
     * @param applicationDate 
     * setter for applicationDate
     */
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    /**
     * 
     * @return 
     * getter for status, return "No Status" if null
     */
    public String getStatus() {
        return Optional.ofNullable(""+status)
                .orElseGet(()->"No Status") ;
    }

    /**
     * setter for status
     * @param statusChar
     * a char indicates the status, valid status are 'N','A','R'
     * @return 
     * 0. no error
     * 1. invalid status 
     * 
     */
    public int setStatus(char statusChar) {
        statusChar = Character.toUpperCase(statusChar); 
        switch (statusChar){
                //error code 0 indicates no error
                case 'N':
                    this.status = Status.NEW;
                    return 0;
                case 'A':
                    this.status = Status.ACCEPTED;
                    return 0;
                case 'R':
                    this.status = Status.REJECTED;
                    return 0;
                default:
                    //if other char, return error code 1
                    return 1;
        }
    }
    /**
     * 
     * @return 
     * getter for remarks, return "No Remarks" if null
     */
    public String getRemarks() {
        return Optional.ofNullable(remarks)
                .orElseGet(()->"No Remarks") ;
    }
    /**
     * setter for remarks
     * @param remarks 
     * A String represents the remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    /**
     * print out the application details
     */
    public String toString() {
        return  "\napplicationID      : " + getApplicationID() + 
                "\napplicationDate    : " + getApplicationDate() + 
                "\nstatus             : " + getStatus() + 
                "\nVolunteer Username : " + getVolunteerID()+
                "\nremarks            : " + getRemarks();
    }
    
    
}

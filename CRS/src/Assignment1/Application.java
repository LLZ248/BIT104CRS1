/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param volunteer 
     */
    public Application(String volunteerID) {
        setApplicationID();
        setVolunteerID(volunteerID);
        setApplicationDate(LocalDate.now());
        setStatus('N');
        setRemarks("No remark yet");
    }

    public String getApplicationID() {
        return Optional.ofNullable(applicationID)
                .orElseGet(()->"No ApplicationID") ;
    }

    public void setApplicationID() {
        this.applicationID = String.format("AP%05d",
                Application.applicationIDCount);
        Application.applicationIDCount++;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }

    public String getApplicationDate() {
        return Optional.ofNullable(""+applicationDate)
                .orElseGet(()->"No ApplicationDate") ;
    }

    /**
     * 
     * @param applicationDate 
     */
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return Optional.ofNullable(""+status)
                .orElseGet(()->"No Status") ;
    }

    /**
     * 
     * @param statusChar
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

    public String getRemarks() {
        return Optional.ofNullable(remarks)
                .orElseGet(()->"No Remarks") ;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return  "\napplicationID      : " + getApplicationID() + 
                "\napplicationDate    : " + getApplicationDate() + 
                "\nstatus             : " + getStatus() + 
                "\nVolunteer Username : " + getVolunteerID()+
                "\nremarks            : " + getRemarks();
    }
    
    
}

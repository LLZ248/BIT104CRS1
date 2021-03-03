package Assignment1;
import java.util.Optional;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Lee Lin Zheng B1802130<br>
 * represents the staff 
 * A staff is created by the CRS Manager.
 * The staff can create trip and manage the applications of the trip
 */
public class Staff extends User{
    private String position;
    private LocalDate dateJoined;
    
    /**
     * 
     * @param position String
     * @param dateJoined LocalDate 
     * @param username String
     * @param password String
     * @param name String
     * @param phone String
     */
    public Staff(String position, LocalDate dateJoined, String username,
            String password, String name, String phone) {
        super(username, password, name, phone);
        setPosition(position);
        setDateJoined(dateJoined);
    }
    
    /**
     * 
     * @return String position, "No Position" if null
     */
    public String getPosition() {
        return Optional.ofNullable(position)
                .orElseGet(()->"No Position");
    }

    /**
     * setter for position
     * @param position String
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * getter for dateJoined
     * @return dateJoined in String, "No dateJoined" if null
     */
    public String getDateJoined() {
        return Optional.ofNullable(""+dateJoined)
                .orElseGet(()->"No dateJoined");
    }

    /**
     * setter for dateJoined
     * @param dateJoined LocalDate
     */
    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }
    
    public String viewTrips(List<Trip> tripList){
        String result ="";
        Iterator it = tripList.iterator();
        while(it.hasNext()){
            Trip trip = (Trip)it.next();
            result = result + trip+"\n Applications:\n";
            String result2 = trip.
                    getApplicationDetails().stream().map(Object::toString)
                    .collect(Collectors.joining("\n\n"));
            result2=result2.isBlank()?"No application yet":result2;
            result = result+"\n---------------------------------\n"
                    +result2+"\n---------------------------------\n";
        }
        return result;
    }
    

    @Override
    public String toString() {
        return "\nStaff Information: " + super.toString()+
                "\nposition     : " + getPosition() +
                "\ndateJoined   : " + getDateJoined() ;
    }
    
    
}

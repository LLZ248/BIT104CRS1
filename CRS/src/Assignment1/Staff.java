package Assignment1;
import java.util.Optional;
import java.time.LocalDate;
/**
 *
 * @author LLZ
 */
public class Staff extends User{
    private String position;
    private LocalDate dateJoined;
    
    /**
     * 
     * @param position
     * @param dateJoined
     * @param username
     * @param password
     * @param name
     * @param phone 
     */
    public Staff(String position, LocalDate dateJoined, String username,
            String password, String name, String phone) {
        super(username, password, name, phone);
        setPosition(position);
        setDateJoined(dateJoined);
    }

    public String getPosition() {
        return Optional.ofNullable(position)
                .orElseGet(()->"No Position");
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDateJoined() {
        return Optional.ofNullable(""+dateJoined)
                .orElseGet(()->"No dateJoined");
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public String toString() {
        return "\nStaff Information: " + super.toString()+
                "\nposition     : " + getPosition() +
                "\ndateJoined   : " + getDateJoined() ;
    }
    
    
}

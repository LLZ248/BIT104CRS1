package Assignment1;

import static Assignment1.CRSConsole.stringToLocalDate;
import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.*;
import java.util.ArrayList;

/**
 * 
 * @author LLZ
 */
public class test1  {

    public static void main(String[] args) {
        User currentUser = null;
        CRS crs = new CRS("Admin","admin");
        Volunteer v1 = new Volunteer("LLZ248", "abc123", "Lee Lin Zheng", "0164487232");
        crs.addVolunteer(v1);
        Staff s1 = new Staff("senior", LocalDate.of(2020,8,24), "EMP0001", "abc123", "Ahamd", "016887545");
        Staff s2 = new Staff("senior", LocalDate.of(2020,8,24), "EMP0002", "abc123", "Ali", "0115487122");
        crs.addStaff(s1);
        crs.addStaff(s2);
        crs.addCRSTrip("EMP0001", new Trip("This is a trip",LocalDate.of(2021, Month.MARCH, 24),"Kuala lumpur",10,'F'));
        crs.addDocumentForVolunteer("LLZ248",new Document('p',LocalDate.of(2022, 4, 24),"Pic1.jpg") );
        crs.addDocumentForVolunteer("LLZ248",new Document('p',LocalDate.of(2022, 4, 24),"Pic1.jpg") );
        crs.addDocumentForVolunteer("LLZ248",new Document('p',LocalDate.of(2022, 4, 24),"Pic1.jpg") );
        currentUser = crs.findUser("LLZ248");
        /*
        String targetUsername = "LLZ248";
        try{
            crs.isStaff(targetUsername);
        }catch(NoSuchElementException e){
            System.out.println("The user with username: "+targetUsername+" does not exist");
        }catch(RuntimeException e){
            System.out.println("The user with username: "+targetUsername+" is not staff");
        }*/
        

        System.out.println(currentUser);
        /*
        try{
            crs.changeUsername("afdsaf", "alibaba");
        }catch(Exception e){
            System.out.println("No such user");
        }
        System.out.println(currentUser);
        System.out.println(crs.viewTrips());*/
        //System.out.println(crs.viewVolunteers());
        //System.out.println(crs.viewStaffs());
        
        
        
    }
    
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



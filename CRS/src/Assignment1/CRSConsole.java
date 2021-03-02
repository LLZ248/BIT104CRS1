package Assignment1;

import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.stream.*;
import java.time.format.*;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Lee Lin Zheng B1802130
 */
public class CRSConsole {
    /**
     * create a static scanner which will be referred as sc 
     */
    static Scanner sc = new Scanner(System.in);
    static CRS crs;
    static User currentUser = null;
    static CRS.UserType currentUserType =null;

    /**
     * Main method of the CRSConsole
     * @param args arguments
     */
    public static void main(String[] args) {
        //Setting up the admin username and password
        System.out.print("Enter Admin Username: ");
        String adminUsername = sc.next();
        System.out.print("Enter Admin Password: ");
        String adminPassword = sc.next();
        crs = new CRS(adminUsername,adminPassword);
        
        Volunteer v1 = new Volunteer("LLZ248", "abc123", "Lee Lin Zheng", "0164487232");
        crs.addVolunteer(v1);
        Staff s1 = new Staff("senior", LocalDate.of(2020,8,24), "EMP0001", "abc123", "Ahamd", "016887545");
        Staff s2 = new Staff("senior", LocalDate.of(2020,8,24), "EMP0002", "abc123", "Ali", "0115487122");
        crs.addStaff(s1);
        crs.addStaff(s2);
        crs.addCRSTrip("EMP0001", new Trip("This is a trip",LocalDate.of(2021, 3, 24),"Kuala lumpur",10,'F'));
        crs.addDocumentForVolunteer("LLZ248",new Document('p',LocalDate.of(2022, 4, 24),"Pic1.jpg") );
        crs.addDocumentForVolunteer("LLZ248",new Document('p',LocalDate.of(2022, 4, 24),"Pic1.jpg") );
        crs.addDocumentForVolunteer("LLZ248",new Document('p',LocalDate.of(2022, 4, 24),"Pic1.jpg") );
        
        while(true){
            if(menu1()) break;  
        }
        //quit application
        System.out.println("Quitting CRSconsole.......");
        System.out.println("See you next time");
        
    }
    
    /**
     * display the main page menu for the user and call respective methods 
     * depending on user's choice
     * @return true if quit, false if not quit
     */
    public static boolean menu1(){
        String crsLogo =(
                "\n===================================================="+
                "\n------+++++++------++++++++++----------++++++++-----"+
                "\n------+++++++------++++++++++----------++++++++-----"+
                "\n--++++-------++++--++++------++++--++++--------++++-"+
                "\n--++++-------++++--++++------++++--++++--------++++-"+
                "\n--++++-------------++++------++++--++++-------------"+
                "\n--++++-------------++++------++++--++++-------------"+
                "\n--++++-------------++++++++++----------++++++++-----"+
                "\n--++++-------------++++++++++----------+++++++------"+
                "\n--++++-------------++++------++++--------------++++-"+
                "\n--++++-------------++++------++++--------------++++-"+
                "\n--++++------++++---++++------++++--++++--------++++-"+
                "\n--++++------++++---++++------++++--++++--------++++-"+
                "\n------++++++-------++++------++++------++++++++-----"+
                "\n------++++++-------++++------++++------++++++++-----"+
                "\n====================================================")
                .replace("-"," ");
        
        boolean repeat = true;
        boolean quit = false;
        while (repeat && !quit){
            System.out.println(crsLogo);
            System.out.println("<<<---Welcome to Crisis Relief "
                + "Services Application--->>>");
            System.out.print(
                        "\nSign up as Volunteer  [S]" +
                        "\nLogin                 [L]" +
                        "\nQuit                  [Q]" +
                        "\nYour choice            : ");
            switch(sc.next().charAt(0)){
                case 's':case 'S':
                    System.out.println("Please enter your personal "
                            + "information");
                    signUpAsVolunteer();
                    break;
                case 'L':case 'l':
                    System.out.println("Please login your account:\n");
                    repeat = !handleLogin();
                    break;
                case 'q':case'Q':
                    repeat = false;
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }  
        }
        if(quit == false){
            repeat = true;
            while(repeat){
                repeat = menu(currentUserType);
            }
        }
        
        return quit;
    }
    
    /**
     * display the specific menu page for different type of user, then invoke
     * the respective handle user method to handle user's choice
     * @param uType UserType Enum instance
     * @return true if the user wishes to continue, false if user quits 
     * this menu
     */
    public static boolean menu(CRS.UserType uType){
        System.out.println("\nList of Available Services :");
        switch (uType){
            case STAFF:
                System.out.println("[1]Organize Trip");
                System.out.println("[2]Manage Applications");
                System.out.println("[3]View Applications");
                System.out.println("[Q/q]sign out [BACK TO MAIN PAGE]");
                System.out.print("Your choice : ");
                return handleStaffMenu(sc.next().charAt(0));
                
                
            case VOLUNTEER:
                System.out.println("[1]Manage Your Profile");
                System.out.println("[2]Apply for Trip");
                System.out.println("[3]View Application Status");
                System.out.println("[Q/q]sign out [BACK TO MAIN PAGE]");
                System.out.print("Your choice : ");
                return handleVolunteerMenu(sc.next().charAt(0));
                
            case MANAGER:
                System.out.println("[1]Record New Staff");
                System.out.println("[2]View All Trips and Applications");
                System.out.println("[3]View All Staff");
                System.out.println("[4]View All Volunteer");
                System.out.println("[Q/q]sign out [BACK TO MAIN PAGE]");
                System.out.print("Your choice : ");
                return handleAdminMenu(sc.next().charAt(0));
                
        }
        return false;
    }
    /**
     * This method will handle the methods for Manager
     * @param choice char represent user choice
     * @return a boolean object represent quit or not
     */
    public static boolean handleAdminMenu(char choice){
        switch(choice){
            case '1':
                recordCRSStaff();
                return true;
            case '2':
                System.out.println(crs.viewTrips());
                return true;
            case '3':
                System.out.println(crs.viewStaffs());
                return true;
            case '4':
                System.out.println(crs.viewVolunteers());
                return true;
            case 'Q':case'q':
                quit();
                return false;
            default:
                System.out.println("Invalid choice!");
                return true;
        
        }
    }
    
    /**
     * This method will handle the methods for Staff
     * @param choice char represent user choice
     * @return a boolean object represent quit or not
     */
    public static boolean handleStaffMenu(char choice){
        switch(choice){
            case '1':
                organizeTrip();return true;
            case '2':
                manageApplication();return true;
            case '3':
               viewApplication();return true;
            case 'q':case'Q':
                quit();
                return false;
            default:
                System.out.println("Invalid choice!");
                return true;
        
        }
    }
    
    /**
     * A Staff method to organize trips. Will ask input from user for the trip
     * information.
     */
    public static void organizeTrip(){
        String description,location,tripDate;
        char crisisType = 'T';int numOfVolunteer = 0;
        description=location=tripDate= "";
        System.out.println("\nEnter Trip Details");
        
        //description
        sc.nextLine();
       description= askForNonNullStringInput("Enter Trip Description: ",
               "Invalid Description:Empty Description!");
       //location
       location= askForNonNullStringInput("Enter Trip Location: ",
               "Invalid Location:Empty Location!");
       //tripDate
       boolean valid =false;
       while(!valid){
            System.out.print("Enter the Trip Date [dd/MM/yyyy] : ");
            tripDate = sc.nextLine();
            if (!tripDate.isBlank()){
                valid = crs.isValidDate(tripDate);
                if(!valid)System.out.println("Invalid Date");
            }else{
                System.out.println("Error:Null Date!");
            }
                
        }valid =false;
       //crisis type
       while(!valid){
            System.out.print("Crisis type? [F]LODD "
                    + "[E]ARTHQUAKE [W]ILDFIRE : ");
            crisisType = Character.toLowerCase(sc.next().charAt(0));
            switch(crisisType){
                case'f':case'e':case'w':
                    valid=true;break;
                default:
                    System.out.println("Invalid type!!");break;
            }
       }valid =false;
       //number of volunteers
       while(!valid){
           
           while(true){
               try{
                    System.out.print("Number of volunteers required : ");
                    numOfVolunteer = sc.nextInt();
                    break;
                }catch(Exception e){
                    System.out.println("Answer must be a number!");
                    sc.next();
                }
           }
            if(numOfVolunteer<=0)System.out.println("Invalid number! "
                + "Must be greater than 0");
             else valid = true;
       }
       
       //display the trip informtaion for confirmation
       Trip dummyTrip = new Trip(description,stringToLocalDate(tripDate),
               location,numOfVolunteer,crisisType);
        System.out.println(dummyTrip);
        System.out.print("Is the Trip information correct? "
                + "[Y]es/[N]o : ");
        if(Character.toLowerCase(sc.next().charAt(0))=='y'){
            crs.addCRSTrip(currentUser.getUsername(),dummyTrip);
            System.out.println("Trip created...");
        } 
        else
            System.out.println("Cancelled...");
    }
    
    /**
     * Staff method. Display all the trips and applications of the trips 
     * which are organized by the Staff. return result which is boolean[2] 
     * array.<br>result[0] indicates is there any trip.<br>
     * result[1] indicates is there any application.
     * @return boolean[] array with size of 2
     */
    public static boolean[] viewApplication(){
        boolean[] result = {false,false};
        Optional<List<String>> tripIDs = 
                crs.searchTripsforStaff(currentUser.getUsername());
        if(tripIDs.isEmpty()){
            System.out.println("The staff has not recorded any trip yet");
            return result;
        }else{
            result[0] = true;
            List<Trip> trips = new ArrayList<>();
            tripIDs.get().forEach(tID -> trips.add(crs.getCRSTrips().get(tID)));
            if(trips.stream().anyMatch(t->!t.hasNoApplication())){
                result[1] = true;}
            System.out.println(currentUser.viewTrips(trips));
            return result;
        }
    }
    
    /**
     * Staff method. Display the trips and applications of the trips by the 
     * staff and manage those application.
     */
    public static void manageApplication(){
        boolean[] check = viewApplication();
        if(check[0]&&check[1]){
            String tripID=null;String appID=null;
            
            //ask for application id to be processed
            System.out.print("Enter the Application ID that you "
                + "wished to process: ");
            appID = sc.next().toUpperCase();
            
           //ask for choice
            char choice;
            while(true){
                System.out.print("[A]ccept or [R]eject ? ");
                choice = Character.toUpperCase(sc.next().charAt(0));
                if (choice == 'A'||choice =='R')break;
                System.out.println("Invalid choice!!");
            }
            //remarks
            System.out.print("Enter remarks [Press Enter if "
                    + "no remarks]: ");
            sc.nextLine();
            String remarks = sc.nextLine();
            remarks=remarks.isBlank()?null:remarks;
            

            switch(crs.manageApplication(currentUser.getUsername(), 
                    appID, choice, remarks)){
                case 0:
                    System.out.println("Application status changed"
                        + " successfully.");break;
                case 1:
                    System.out.println(
                            "Invalid Application ID!Possible reasons are:"+ 
                            "\n1.You are not responsible for this trip."+
                            "\n2.Not exisitng application ID.");break;
                case 2:
                    System.out.println("This application has already been "
                        + "processed before!");break;
                default:
                    System.out.println("Somthing is wrong.Pls try again");
                    break;
            }
        }else{
            System.out.println("There is no application yet");
        }
    }
    
    /**
     * This method will handle the Volunteer methods and invoke the 
     * corresponding methods
     * @param choice char represents the user choice
     * @return true if display the menu again, false if user quit 
     */
    public static boolean handleVolunteerMenu(char choice){
        System.out.println("");
        currentUser = (Volunteer)currentUser;
        switch(choice){
            case '1':
                manageVolunteerProfile();return true;
            case '2':
                applyForTrip();return true;
            case '3':
                viewApplicationStatus();return true;
            case 'q':case'Q':
                return false;
            default:
                System.out.println("Invalid choice!");
                return true;
        
        }
    }
    
    /**
     * Volunteer method. Volunteer can change password, phone number, name, 
     * or upload a document.
     */
    public static void manageVolunteerProfile(){
        System.out.println("Your current information");
        System.out.println(currentUser+"\n");
        System.out.println("Enter your choice :");
        System.out.println("[1]Change password ");
        System.out.println("[2]Change phone number ");
        System.out.println("[3]Change name");
        System.out.println("[4]Upload document");
        System.out.print("Your choice : ");
        
        switch(sc.next().charAt(0)){                
            case '1':
                sc.nextLine();
                String newPwsd = askForNonNullStringInput(
                        "Enter your new password :"
                        ,"Invalid password:Null Password!");
                currentUser.setPassword(newPwsd);
                System.out.println("Password Changed.");
                System.out.println("Your current information");
                System.out.println(currentUser);
                break;
            case '2':
                sc.nextLine();
                String newPhoneNumber = askForPhoneNum(
                        "Enter your new phone number :");
                currentUser.setPhone(newPhoneNumber);
                System.out.println("Phone number Changed.");
                System.out.println("Your current information");
                System.out.println(currentUser);
                break;
            case '3':
                sc.nextLine();
                String newName = askForNonNullStringInput(
                        "Enter your new name :",
                        "Invalid Name:Null Name!");
                currentUser.setName(newName);
                System.out.println("Name Changed.");
                System.out.println("Your current information");
                System.out.println(currentUser);
                break;
            case '4':
                System.out.println("Document information :");
                boolean valid = false;
                char docType = 'V';
                String docExpireDate,docPicture;
                docExpireDate ="";
                //document type
                while(!valid){
                    System.out.print("[P]assport\n[C]ertificate\n[V]isa\n"
                        + "Enter your document type : ");
                    docType = Character.toUpperCase(sc.next().charAt(0));
                    switch(docType){
                        case'P':case'C':case'V':
                            valid = true;break;
                        default:
                            System.out.println("Invalid type!");break;
                    }
                }valid = false;
                //expire date
                sc.nextLine();
                while(!valid){
                    System.out.print("Enter the cert expire date "
                            + "[dd/MM/yyyy] "
                            + "press [Enter] if no expire date: ");
                    docExpireDate = sc.nextLine();
                    if (docExpireDate.isBlank()){
                        valid = true;
                    }else{
                        valid = crs.isValidDate(docExpireDate);
                        if(!valid)System.out.println("Invalid Date");
                    }
                }
                //picture
                System.out.print("Picture file : ");
                docPicture = sc.next();
                Document dummyDocument;
                if(docExpireDate.isBlank()){
                    dummyDocument = new Document(docType,docPicture);
                }else{
                    dummyDocument = new Document(docType,
                            stringToLocalDate(docExpireDate),docPicture);
                }
                System.out.println(dummyDocument);
                System.out.println("Is the document information correct? "
                        + "[Y]es/[N]o");
                if(Character.toLowerCase(sc.next().charAt(0))=='y')
                   crs.addDocumentForVolunteer(currentUser.getUsername(),
                           dummyDocument);
                else
                    System.out.println("Cancelled...");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
    
    /**
     * Volunteer method. The volunteer selects to view all trips, and can 
     * select one to join. If there is still space available, an application 
     * is created for the Volunteer to join the Trip. The applicationID 
     * is generated and the application date is set to the system date. 
     * The status is set to “NEW”.
     */
    public static void applyForTrip(){
        System.out.println(currentUser.viewTrips(crs.getCRSTrips().values()
                .stream().collect(Collectors.toList())));
        if (crs.addVolunteerIntoTrip(null,null) ==1){
            System.out.println("No trip is currently available.");
        }
        else{
            currentUser.viewTrips(crs.getCRSTrips().values().stream()
                    .collect(Collectors.toList()));
            System.out.print("Enter the Trip ID : ");
            String tripID = sc.next().toUpperCase();

            switch(crs.addVolunteerIntoTrip(currentUser.getUsername(),tripID)){
                case 0:
                    System.out.println("Application submitted "
                        + "successfully.");break;
                case 2:
                    System.out.println("You have already applied for "
                            + "this trip!");break;
                case 3:
                    System.out.println("The Trip is already full!");break;
                case 4:
                    System.out.println("The Trip with the trip ID "+
                            tripID+" is not found");break;
                case 5:
                    System.out.println("You have already applied for a trip "
                            + "which is on the same day");break;
                default:
                    break;
            }

        }              
    }
    
    /**
     * Volunteer method.Volunteer wants to check the status of 
     * his or her applications. The volunteer select to view the trips that 
     * they have applied for. The trip date, description and 
     * application status and remarks will be shown. 
     */
    public static void viewApplicationStatus(){
        System.out.println(crs.viewApplicationStatus(currentUser.
                getUsername()));
    }
    
    /**
     * Method to handle login for all users. Ask for username and password, 
     * then return the username and password in String array
     * @return uInfo
     */
    public static String[] login(){
        System.out.print("Enter your username : ");
        String uName = sc.next().toUpperCase();
        System.out.print("Enter your password : ");
        String uPass = sc.next();
        String[] uInfo = {uName,uPass};
        return uInfo;
    }

    /**
     * method to handle user login. If success login the current user will be
     * set as the success login user.
     * @return true is success login,false if invalid login, wrong username or
     * password
     */
    public static boolean handleLogin(){
        String[] uInfo = new String[2];
        int errorCode = 1;

        uInfo = login();
        errorCode = crs.veifyLogin(uInfo);
        String username,password;
        username = uInfo[0];
        password = uInfo[1];
        switch (errorCode){
            //valid login case
            case 0 :
                currentUser = crs.findUser(username);
                try{
                    crs.isVolunteer(username);
                    //volunteer
                    currentUserType = CRS.UserType.VOLUNTEER;
                }catch(RuntimeException e){
                    //staff
                    currentUserType = CRS.UserType.STAFF;
                }
                System.out.println("Login Successfully");
                System.out.println("Hi, "+currentUser.getName());
                return true;
            //invalid login cases
            case 1:
                System.out.println("Invalid Username!");
                return false;
            case 2:
                System.out.println("Wrong Password");break;
            //admin login case
            case -99:
                currentUserType = CRS.UserType.MANAGER;
                System.out.println("Login Successfully");
                System.out.println("Hi, Admin");
                return true;
            default:
                return false;
        }          
        return false;
    }
    /**
     * A method to print and ask username from user, if empty username is 
     * given, then the user will be asked to prompt the username again.
     * The username entered will be converted into uppercase.
     * 
     * @param message String
     * @return username
     */
    public static String askForUsername(String message){
        Optional<String> uName = Optional.empty();
        boolean valid = false;
        while(!valid || uName.isEmpty()){
        System.out.print(message);
        uName = Optional.ofNullable(sc.next().toUpperCase());
        valid = crs.isValidUsername(uName);
        if(!valid)System.out.println("Invalid Username");
        }
        return uName.get();
    }
    /**
     * Ask for phone number and validate the input. If invalid, ask again.
     * Valid phone number is all numbers
     * @param message String.
     * @return the phone number input
     */
    public static String askForPhoneNum(String message){
        Optional<String> phone = Optional.empty();
        boolean valid = false;
        while(!valid || phone.isEmpty()){
            System.out.print(message);
            phone = Optional.ofNullable(sc.nextLine());
            if(phone.isEmpty())System.out.println("Invalid phone! "
                    + "Null phone!");
            if(phone.isPresent()){
                valid= crs.isValidPhone(phone.get());
                if(!valid)System.out.println("Phone number should only "
                        + "consist of numbers");;
            }
        }
        return phone.get();
    }
   
    /**
     * A method to ask for user to prompt a not null input, if empty ask again.
     *@param inputMessage String. Message that will be displayed when asking
     * for input.
     * @param nullErrorMessage String message that will be displayed if null
     * value is given
     * @return input
     */
    public static String askForNonNullStringInput(String inputMessage
            ,String nullErrorMessage){
        Optional<String> input = Optional.empty();
        while(input.orElse("").isBlank()){
            System.out.print(inputMessage);
            input = Optional.ofNullable(sc.nextLine());
            if(input.get().isBlank())System.out.println(nullErrorMessage);
        }
        return input.get();
    }
    
    /**
     * Method that ask user for a date input, then check whether is the input
     * date valid. If invalid, ask the user to input again until valid date
     * is given.
     * @return staffDate
     */
    public static String askForStaffDate(){
        String staffDate = "";
        boolean valid = false;
        while(!valid){
            System.out.print("Enter the staff joined date [dd/MM/yyyy] : ");
            staffDate = sc.next();
            valid = crs.isValidDate(staffDate);
            if(!valid)System.out.println("Invalid Date");
        }
        return staffDate;
    }
    
    /**
     * This method allows guest to sign up for Volunteer.
     * The volunteer has to enter valid username, password,
     * name and phone number.
     */
    public static void signUpAsVolunteer(){
        String uName,password,name,phone;

        uName=askForUsername("Enter your username : ");
        sc.nextLine();
        password=askForNonNullStringInput("Enter your password : ",
                "Invalid password:Null password!");
        name=askForNonNullStringInput("Enter your name : ",
                "Invalid name:Null name!"); 
        phone=askForPhoneNum("Enter your phone number : ");

        System.out.println("Please confirm your personal information: ");
        Volunteer dummyVolunteer = new Volunteer(uName,password,name,phone);
        System.out.println(dummyVolunteer);
        
        System.out.print("\n Confrim Registration?\nYES[Y]/NO[N] : ");
        boolean isConfirmed = (Character.toLowerCase(sc.
                next().charAt(0))=='y')?true:false;
        if(isConfirmed){
            crs.addVolunteer(dummyVolunteer);
            System.out.println("Successful registered.");
        }else{
            System.out.println("Registration cancelled.");
        }
    }
    
    /**
     * A method for CRS Manager to record a new staff member. 
     * The CRS Manager enters the staff’s username, 
     * password, name, phone, position and date joined.  
     */
    public static void recordCRSStaff(){
        String staffUsername,staffPassword,
                staffName,staffPhone,staffPosition;
        staffUsername = askForUsername("Enter the staff username : ");
        sc.nextLine();
        staffPassword = askForNonNullStringInput("Enter the staff password : ",
                "Invalid password:Null password!"); 
        staffName=askForNonNullStringInput("Enter the staff name : ",
                "Invalid name:Null name!");
        staffPhone=askForPhoneNum("Enter the staff phone : ");
        staffPosition = askForNonNullStringInput("Enter the staff "
                + "position : ","Invalid position:null position!");
         String staffDate = askForStaffDate();
        Staff dummyStaff = new Staff(staffPosition, 
                stringToLocalDate(staffDate),
                staffUsername,staffPassword,
                staffName,staffPhone);
        System.out.println(dummyStaff);
        System.out.print("\n Confrim Registration?\nYES[Y]/NO[N] : ");
        boolean isConfirmed = (Character.toLowerCase(sc.
                next().charAt(0))=='y')?true:false;
        if(isConfirmed){
            crs.addStaff(dummyStaff);
            System.out.println("The staff is successfully "
                    + "added into the CRS system");
        }else{
            System.out.println("Record cancelled.");
        }
        
    }
    
    /**
     *Convert a string to a LocalDate Object
     *in format "dd/MM/yyyy"
     *@param date String to represent the date
     * @return the LocalDate
     */
    public static LocalDate stringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.
                ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
    
    /**
     * method to handle quit action by the user
     */
    public static void quit(){
        currentUser = null;
        currentUserType =null;
        System.out.println("Logged Out...");
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

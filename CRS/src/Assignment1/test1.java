package Assignment1;

import static Assignment1.CRSConsole.stringToLocalDate;
import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;
import java.time.format.*;
import java.util.ArrayList;

/**
 * 
 * @author LLZ
 */
public class test1  {

    public static void main(String[] args) {
            ArrayList<Trip> tripList1 = new ArrayList<Trip>();
            //tripList1.add(new Trip("test","19/9/2021","Malaysia",20));

            System.out.println(new Trip("test",LocalDate.of(2017,1,13),"Malaysia",20,'f'));
            System.out.println(new Trip(null,LocalDate.of(2018, 3, 24),"Malaysia",20,'e'));
            Trip trip1 = new Trip(null,null,null,20,'e');
            System.out.println(trip1);
            Trip trip2 = new Trip(null,null,null,20,'f');
            System.out.println(trip2);
            Staff v1 = new Staff("Manager",null,null,null,null,null);
            System.out.println(v1);
            
            //Stream<Integer> streamIterated = Stream.iterate(40, n -> n).limit(20);
            //System.out.println(streamIterated.allMatch(element->element.equals(20)));
            //System.out.println(streamIterated.filter(element->element.equals(40)).collect(Collectors.toList()));

            Application a1 = new Application(null);
            System.out.println(a1);

           System.out.println(System.getProperty("user.dir"));

           ArrayList<Optional<Integer>> intArr = new ArrayList<Optional<Integer>>();
           intArr.add(Optional.ofNullable(null));intArr.add(Optional.ofNullable(null));
           intArr.add(Optional.ofNullable(null));intArr.add(Optional.ofNullable(null));

           int found = intArr.stream()
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .findFirst().orElse(0);


           System.out.println("found\n"+found+" results");

           List<String> elements =
            Stream.of("a", "b", "c").filter(element -> element.contains("b")).collect(Collectors.toList());
          Optional<String> anyElement = elements.stream().findAny();
          Optional<String> firstElement = elements.stream().findFirst();
          System.out.println(elements);
          System.out.println(elements.stream().map(e->e.equals("c")).findFirst());

          Hashtable<Integer, String> hash_table =  
            new Hashtable<Integer, String>(); 

            // Inserting elements into the table 
            hash_table.put(10, "Geeks"); 
            hash_table.put(15, "4"); 
            hash_table.put(20, "Geeks"); 
            hash_table.put(25, "Welcomes"); 
            hash_table.put(30, "You"); 

            // Displaying the Hashtable 
            System.out.println("Initial table is: " + hash_table); 

            // Displaying the size of the table 

            hash_table.forEach((k,v)-> {
                if(k == 10){
                        hash_table.replace(k, "Replaced");
                    }
                System.out.println("Key : " + k + ", Value : " + v);
            });
            hash_table.forEach( 
        (k, v) -> System.out.println("Key : " + k + ", Value : " + v));


         LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse("24/08/2001", formatter);
        System.out.println(date);
        System.out.println(text);
        System.out.println(parsedDate);
        System.out.println("Hashtable test\n");
        Hashtable<String,List<String>> ht1 = new Hashtable<>();
        addKAndListVintoHashtable(ht1,"1","abc123456789");
        addKAndListVintoHashtable(ht1,"2","abc1234");
        ht1.get("1").add("abc123");
        System.out.println(ht1.get("1").size());
        ht1.values().forEach(v->v.forEach(p->System.out.println(p)));
        
        Hashtable<String,User> CRSUsers = new Hashtable<>();
        CRSUsers.put("LLZ248",new Volunteer("llz248","abc123","Lee Lin Zheng"
                ,"0164487232"));
        CRSUsers.put("LLZ2248",new Staff("Senior",
                stringToLocalDate("12/02/2020")
                ,"LLZ248","abc123","Lee Lin Zheng","0164487232"));
        String result = CRSUsers.values().stream().filter(u->u instanceof Staff)
                .map(u->(Staff)u)
                .map(Object::toString).collect(Collectors.joining("\n"));
        result = result.isBlank()?"No staff is recorded.":result;
        System.out.println(result);
        
        
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new Volunteer("dummy","abc123","dummy","999"));
        userList.add(new Volunteer("dummy2","abc123","dummy2","999"));
        String testName = "dummy2";String testPassword = "abc123";
        boolean verify = userList.stream().anyMatch(u->u.getUsername().equalsIgnoreCase(testName)&&u.getPassword().equals(testPassword));
        System.out.println(verify);
    }
    /**
     * 
     * @param ht
     * @param k
     * @param v 
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



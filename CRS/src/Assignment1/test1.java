package Assignment1;

import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.*;
import java.time.format.*;
import org.apache.commons.collections4.map.MultiValueMap;


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
            System.out.println(v1.viewTrips(tripList1));
            //Stream<Integer> streamIterated = Stream.iterate(40, n -> n).limit(20);
            //System.out.println(streamIterated.allMatch(element->element.equals(20)));
            //System.out.println(streamIterated.filter(element->element.equals(40)).collect(Collectors.toList()));
            Volunteer v2 = new Volunteer(null,null,null,null);
            Application a1 = new Application(v2);
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
        MultiValueMap<String,String> ht1 = new MultiValueMap<>();
        ht1.put("1", "1212121");
        ht1.put("1", "abc123");
        System.out.println(String.join("\n", hash_table.values()));
        ht1.getCollection("1").forEach(p->System.out.println(p));
        


    }
}


package Assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author Lee Lin Zheng B1802130<br>
 * represents a volunteer 
 * Volunteer can apply application for trip
 */
public class Volunteer extends User{
        ArrayList<Document> documentArr;
        
        /**
         * 
         * @param username String object
         * @param password String object
         * @param name String object
         * @param phone  String object
         */
	public Volunteer(String username, String password, 
			String name, String phone) {
		super(username, password, name, phone);
                documentArr = new ArrayList<Document>();
	}
	
        /**
         * Add a document into the documentArr
         * @param theDocument  A Document object
         * @return theDocument this will return a Document object
         */
        public Document addDocument(Document theDocument){
            documentArr.add(theDocument);
            return theDocument;
        }
        
        /**
         * 
         * @return documentArr return a list of Document
         */
        public ArrayList<Document> getDocumentArr() {
            return documentArr;
        }
        
        /**
         * get a list of documents of the user in string, empty if no document
         * @return result 
         */
        public String getDocumentArrAsString(){
            String result = getDocumentArr().stream().map(Object::toString)
                    .collect(Collectors.joining("\n"));
            result = result.isBlank()?"":result;
            return result;
        }
       
        /**
         * return a list of trips in string to be viewed by the volunteer
         * @param tripList the trip list
         * @return all trips in string
         */
        public String viewTrips(List<Trip> tripList){
            return tripList.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
        }
        
	@Override
	public String toString() {
                String result="\nVolunteer Information:"+super.toString();
                String result2 = getDocumentArrAsString();
                result2 = result2.isBlank()?"No Document yet":result2;
                result = result +"\nDocument Information: \n"+result2;
                return result;
	}
	
	
	
}

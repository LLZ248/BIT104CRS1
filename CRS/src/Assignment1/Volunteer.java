package Assignment1;

import java.util.Optional;
import java.util.ArrayList;

/**
 * @author LLZ
 *
 */
public class Volunteer extends User{

	/**
	 * @param username
	 * @param password
	 * @param name
	 * @param phone
	 */
        ArrayList<Document> documentArr = new ArrayList<Document>();
        
	public Volunteer(String username, String password, 
			String name, String phone) {
		super(username, password, name, phone);
	}
	
        public Document addDocument(Document theDocument){
            documentArr.add(theDocument);
            return theDocument;
        }

        public ArrayList<Document> getDocumentArr() {
            return documentArr;
        }
        

	@Override
	public String toString() {
                
		return "\nVolunteer Information:"
                        +super.toString();
	}
	
	
	
}

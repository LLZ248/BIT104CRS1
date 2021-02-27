package Assignment1;

import java.util.Optional;
import java.util.ArrayList;

/**
 * @author LLZ
 *
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
        
        
	@Override
	public String toString() {
                
		return "\nVolunteer Information:"
                        +super.toString();
	}
	
	
	
}

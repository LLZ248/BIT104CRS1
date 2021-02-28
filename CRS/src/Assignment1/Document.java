package Assignment1;
import java.time.LocalDate;
import java.util.Optional;
/**
 *
 * @author Lee Lin Zheng B1802130
 */
public class Document {
    
    
    enum DocumentType {
        PASSPORT,
        CERTIFICATE,
        VISA
    }
    
    private DocumentType documentType;
    private LocalDate expiryDate;
    private String image;
    
    /**
     * 
     * @param documentType
     * A String object represents the type of the document
     * @param expiryDate
     * A LocalDate object represents the expiry date of the document
     * @param image 
     * A String object represents the image file
     */
    public Document(char documentType, LocalDate expiryDate, String image) {
        setDocumentType(documentType);
        setExpiryDate(expiryDate);
        setImage(image);
    }
    /**
     * 
     * @param documentType
     * A String object represents the type of the document
     * @param image 
     * A String object represents the image file
     */
    public Document(char documentType, String image) {
        setDocumentType(documentType);
        setImage(image);
    }
    /**
     * getter for documentType
     * @return documentType
     */
    public DocumentType getDocumentType() {
        return documentType;
    }
    /**
     * setter for 
     * @param documentType documentType
     */
    public void setDocumentType(char documentType) {
        switch (documentType){
            case 'P':case'p':
                this.documentType = DocumentType.PASSPORT;break;
            case 'c':case'C':
                this.documentType = DocumentType.CERTIFICATE;break;
            case 'v':case'V':
                this.documentType = DocumentType.VISA;break;
            default:
                break;
        }
        
    }
    /**
     * 
     * @return 
     * return the expiry date of the document in string,
     * return "No Expiry Date" if null
     */
    public String getExpiryDate() {
        return Optional.ofNullable(""+expiryDate)
                .orElseGet(()->"No Expiry Date") ;
    }
    /**
     * setter for expiryDate
     * @param expiryDate 
     * A LocalDate object is required
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    /**
     * getter for image
     * @return 
     * return a String object
     */
    public String getImage() {
        return image;
    }
    /**
     * setter for image
     * @param image 
     * A String object represents the image file is required
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * 
     * @return 
     * the details of the document
     */
    @Override
    public String toString() {
        return  "\nDocument Type  : " + getDocumentType() + 
                "\nExpiry Date    : " + getExpiryDate() + 
                "\nImage          : " + getImage();
                
    }
    
    
    
}

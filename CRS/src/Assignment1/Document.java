package Assignment1;
import java.time.LocalDate;
import java.util.Optional;
/**
 *
 * @author LLZ
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
     * @param expiryDate
     * @param image 
     */
    public Document(char documentType, LocalDate expiryDate, String image) {
        setDocumentType(documentType);
        setExpiryDate(expiryDate);
        setImage(image);
    }
    /**
     * 
     * @param documentType
     * @param image 
     */
    public Document(char documentType, String image) {
        setDocumentType(documentType);
        setImage(image);
    }
    /**
     * 
     * @return 
     */
    public DocumentType getDocumentType() {
        return documentType;
    }
    /**
     * 
     * @param documentType 
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
     */
    public String getExpiryDate() {
        return Optional.ofNullable(""+expiryDate)
                .orElseGet(()->"No Expiry Date") ;
    }
    /**
     * 
     * @param expiryDate 
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    /**
     * 
     * @return 
     */
    public String getImage() {
        return image;
    }
    /**
     * 
     * @param image 
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return  "\nDocument Type  : " + getDocumentType() + 
                "\nExpiry Date    : " + getExpiryDate() + 
                "\nImage          : " + getImage();
                
    }
    
    
    
}

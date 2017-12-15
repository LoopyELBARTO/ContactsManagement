import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//This would allow the Marshalling of java objects into XML format
@XmlRootElement(name = "businesses")
public class BusinessListWrapper {
    private List<Business> business;

    @XmlElement(name = "business")
    public List<Business> getBusiness(){
        return business;
    }

    public void setBusiness(List<Business> business){
        this.business = business;
    }
}

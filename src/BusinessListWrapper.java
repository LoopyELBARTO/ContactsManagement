import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "business")
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

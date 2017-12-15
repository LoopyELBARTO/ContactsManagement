import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Business {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty address;
    private final StringProperty businessName;
    private final StringProperty websiteName;
    private final StringProperty email;
    private final StringProperty phoneNumber;

    //Default constuctor


    public Business(){

        this(null, null);
    }

    //This is a dummy example data, but also takes a parameter to set data when its called
    public Business(String businessName, String lastName){
        this.businessName = new SimpleStringProperty(businessName);
        this.lastName = new SimpleStringProperty(lastName);

        this.firstName = new SimpleStringProperty("example");
        this.websiteName = new SimpleStringProperty("example.com");
        this.email = new SimpleStringProperty("email@mail.com");
        this.address = new SimpleStringProperty("123 steet ave");
        this.phoneNumber = new SimpleStringProperty("123456789");
    }
    //Setters and getter for the StringProperties
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
    public String getBusinessName() {
        return businessName.get();
    }

    public StringProperty businessNameProperty() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName.set(businessName);
    }


    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getWebsiteName() {
        return websiteName.get();
    }

    public StringProperty websiteNameProperty() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName.set(websiteName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}

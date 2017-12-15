import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BusinessEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField businessNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField websiteField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;

    private Stage dialogStage;
    private Business business;
    private boolean okClicked = false;

    @FXML
    private void initialize(){

    }

    //This is a setter for the dialogStage when called from the MainApp
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    //This method sets data from what the user iputs in the edits
    public void setBusiness(Business business){
        this.business = business;

        firstNameField.setText(business.getFirstName());
        lastNameField.setText(business.getLastName());
        businessNameField.setText(business.getBusinessName());
        addressField.setText(business.getAddress());
        websiteField.setText(business.getWebsiteName());
        emailField.setText(business.getEmail());
        phoneNumberField.setText(business.getPhoneNumber());
    }

    public boolean isOkClicked(){
        return okClicked;
    }
    //this handles the functionality of the OK button
    @FXML
    private void handleOk(){
        if (isInputValid()){
            business.setFirstName(firstNameField.getText());
            business.setLastName(lastNameField.getText());
            business.setBusinessName(businessNameField.getText());
            business.setAddress(addressField.getText());
            business.setWebsiteName(websiteField.getText());
            business.setEmail(emailField.getText());
            business.setPhoneNumber(phoneNumberField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    //This ensures that the user have entered a valid value in each field
    private boolean isInputValid(){
        String errorMessage = "";
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMessage += "No valid first name! \n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0){
            errorMessage += "No valid last name! \n";
        }
        if (businessNameField.getText() == null || businessNameField.getText().length() == 0){
            errorMessage += "No valid business name! \n";
        }
        if (addressField.getText() == null || addressField.getText().length() == 0){
            errorMessage += "No valid address! \n";
        }
        if (websiteField.getText() == null || websiteField.getText().length() == 0){
            errorMessage += "No valid website entry! \n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0){
            errorMessage += "No valid email was entered";
        }
        if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0){
            errorMessage += "No valid phone number! \n";
        }

        //if the user does not meet the requirements, a error dialog box will appear
        if (errorMessage.length() == 0){
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}

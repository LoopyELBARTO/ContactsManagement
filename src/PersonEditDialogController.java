import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField businessNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    private void initialize(){

    }
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person){
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        businessNameField.setText(person.getBusinessName());
        addressField.setText(person.getAddresss());
        phoneNumberField.setText(person.getPhoneNumber());
    }
    public boolean isOkClicked(){
        return okClicked;
    }
    @FXML
    private void handleOk(){
        if (isInputValid()){
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setBusinessName(businessNameField.getText());
            person.setAddresss(addressField.getText());
            person.setPhoneNumber(phoneNumberField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

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
        if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0){
            errorMessage += "No valid phone number! \n";
        }

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

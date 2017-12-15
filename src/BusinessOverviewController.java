import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BusinessOverviewController {
    @FXML
    private TableView<Business> businessTable;
    @FXML
    private TableColumn<Business, String> businessNameColumn;
    @FXML
    private TableColumn<Business, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label businessLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label websiteLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML

    // Reference to the main application.
    private MainApp mainApp;

    public BusinessOverviewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the business table with the two columns.
        businessNameColumn.setCellValueFactory(cellData -> cellData.getValue().businessNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showBusinessDetail(null);
        businessTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBusinessDetail(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        businessTable.setItems(mainApp.getBusinessData());
    }

    //This allows the detail overview of a selected business
    private void showBusinessDetail(Business business){
        if (business != null){
            firstNameLabel.setText(business.getFirstName());
            lastNameLabel.setText(business.getLastName());
            addressLabel.setText(business.getAddress());
            websiteLabel.setText(business.getWebsiteName());
            emailLabel.setText(business.getEmail());
            businessLabel.setText(business.getBusinessName());
            phoneNumberLabel.setText(business.getPhoneNumber());
        }else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            addressLabel.setText("");
            businessLabel.setText("");
            websiteLabel.setText("");
            emailLabel.setText("");
            phoneNumberLabel.setText("");
        }
    }

    //this handles the delete button when a business is selected from the TableView
    @FXML
    private void handleDeleteBusiness(){
        int selectedIndex = businessTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            businessTable.getItems().remove(selectedIndex);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No Business is Selected");
            alert.setContentText("Please select a business in the table");

            alert.showAndWait();
        }
    }
    //This handles the adding of a new business button
    @FXML
    private void handleNewBusiness(){
        Business tempBusiness = new Business();
        boolean okClicked = mainApp.showBusinessEditDialog(tempBusiness);
        if (okClicked){
            mainApp.getBusinessData().add(tempBusiness);
        }
    }

    //This handles the editing of a business overview details
    @FXML
    private void handleEditBusiness(){
        Business selectedBusiness = businessTable.getSelectionModel().getSelectedItem();
        if (selectedBusiness != null){
            boolean okClicked = mainApp.showBusinessEditDialog(selectedBusiness);
            if (okClicked){
                showBusinessDetail(selectedBusiness);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Business Selectd");
            alert.setContentText("Please select a business in the table. ");

            alert.showAndWait();
        }
    }

    //This launches a dialog box that would enable the user to search for a
    // specific last name or business name
    @FXML
    private void handleBusinessSearch(){
        boolean searchClicked = mainApp.showSearchDialog();
    }

}

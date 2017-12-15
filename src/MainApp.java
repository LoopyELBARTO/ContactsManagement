import ch07.trees.BinarySearchTree;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class MainApp extends Application {


    private Stage window;
    private BorderPane rootLayout;

    protected ObservableList<Business> businessesData = FXCollections.observableArrayList();
    private BinarySearchTree<String> bstBusinessData = new BinarySearchTree<>();

    //Dummy data
    public MainApp(){
        businessesData.add(new Business("Ex.INC", "Ample"));
    }

    //create an observableList with a business data type
    public ObservableList<Business> getBusinessData(){
        return businessesData;
    }
    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        this.window.setTitle("Contacts Management");

        initRootLayout();

        showBusinessOverview();
    }

    //initialize the root of the fxml
    private void initRootLayout() {
        try {
            //create a new loader inorder to load the RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            //create a new scene and setting it to the window/Stage
            Scene scene = new Scene(rootLayout);
            window.setScene(scene);

            //creates a controller so it can be linked to the driver class
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        File file = getBusinessFilePath();
        if (file != null){
            loadBusinessDataFromFile(file);
        }
    }

    private void showBusinessOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BusinessOverview.fxml"));
            AnchorPane businessOverview = loader.load();

            rootLayout.setCenter(businessOverview);

            BusinessOverviewController controller = loader.getController();
            controller.setMainApp(this);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage(){
        return window;
    }

    public boolean showBusinessEditDialog(Business business){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BusinessEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Business");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BusinessEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBusiness(business);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean showSearchDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("SearchDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Search");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SearchDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //controller.showBusinessDetail(business);
            controller.setTableView(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public File getBusinessFilePath(){
        Preferences preferences = Preferences.userNodeForPackage(MainApp.class);
        String filePath = preferences.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        }
        else {
            return null;
        }
    }

    public void setBusinessFilePath(File file){
        Preferences preferences = Preferences.systemNodeForPackage(MainApp.class);
        if (file != null){
            preferences.put("filePath", file.getPath());
            window.setTitle("Contact Management - " + file.getName());
        }else {
            preferences.remove("filePath");
            window.setTitle("Contact Management");
        }
    }
    //JAXB- java architecture for XML

    //UnMarshalling - converting a XML to java Object
    public void loadBusinessDataFromFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(BusinessListWrapper.class);
            //this deserialize xml data to be access by java
            Unmarshaller unmarshaller = context.createUnmarshaller();

            //The wrapper would read xml and unmarshall by passing a file in the parameter
            BusinessListWrapper wrapper = (BusinessListWrapper) unmarshaller.unmarshal(file);

            //this would clear the TableView if there is anything loaded in it
            businessesData.clear();
            //This uses the UnMarshalled file and add it to the observable list - businessData
            businessesData.addAll(wrapper.getBusiness());
            //save the xml
            setBusinessFilePath(file);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    //Marshalling - converting java object to XML
    public void saveBusinessDataToFile(File file){
        try {
            //creating a new instance of JAXBContext and initializing the marshaller
            JAXBContext context = JAXBContext.newInstance(BusinessListWrapper.class);
            Marshaller marshaller = context.createMarshaller();

            //This properly format the XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Creating a new instance of the BusinesListWrapper to be formatted structurally
            BusinessListWrapper wrapper = new BusinessListWrapper();
            wrapper.setBusiness(businessesData);

            //This writes the XML file
            marshaller.marshal(wrapper, file);

            setBusinessFilePath(file);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

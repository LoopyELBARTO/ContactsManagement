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

    private ObservableList<Business> businessesData = FXCollections.observableArrayList();

    public MainApp(){
        businessesData.add(new Business("Ex.INC", "Ample"));
    }

    public ObservableList<Business> getBusinessData(){
        return businessesData;
    }
    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        this.window.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            window.setScene(scene);

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

    private void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BusinessOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

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

    public boolean showPersonEditDialog(Business business){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BusinessEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BusinessEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(business);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
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

    public void loadBusinessDataFromFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(BusinessListWrapper.class);
            //this deserialize xml data to be access by java
            Unmarshaller unmarshaller = context.createUnmarshaller();

            //Read xml and unmarshall
            BusinessListWrapper wrapper = (BusinessListWrapper) unmarshaller.unmarshal(file);

            businessesData.clear();
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
    public void saveBusinessDataToFile(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(BusinessListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            BusinessListWrapper wrapper = new BusinessListWrapper();
            wrapper.setBusiness(businessesData);

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

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import ch07.trees.BSTInterface;
import ch07.trees.BinarySearchTree;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchDialogController {
    BinarySearchTree<String> bstBusiness = new BinarySearchTree<String>();
    Iterator<String> iterator;
    Business business = new Business();

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField lastNameField;

        @FXML
        private TextField businessNameField;

        @FXML
        private TableView<Business> businessSearchTable;

        @FXML
        private TableColumn<Business, String> businessNameColumn;

        @FXML
        private TableColumn<Business, String> lastNameColumn;

        @FXML
        private Label firstNameLabel;

        @FXML
        private Label lastNameLabel;

        @FXML
        private Label businessNameLabel;

        @FXML
        private Label websiteLabel;

        @FXML
        private Label emailLabel;

        @FXML
        private Label addressLabel;

        @FXML
        private Label phoneNumberLabel;

        @FXML
        void initialize() {
            assert lastNameField != null : "fx:id=\"lastNameField\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert businessNameField != null : "fx:id=\"businessNameField\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert businessSearchTable != null : "fx:id=\"businessSearchTable\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert businessNameColumn != null : "fx:id=\"businessNameColumn\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert lastNameColumn != null : "fx:id=\"lastNameColumn\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert firstNameLabel != null : "fx:id=\"firstNameLabel\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert lastNameLabel != null : "fx:id=\"lastNameLabel\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert businessNameLabel != null : "fx:id=\"businessNameLabel\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert websiteLabel != null : "fx:id=\"websiteLabel\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert addressLabel != null : "fx:id=\"addressLabel\" was not injected: check your FXML file 'SearchDialog.fxml'.";
            assert phoneNumberLabel != null : "fx:id=\"phoneNumberLabel\" was not injected: check your FXML file 'SearchDialog.fxml'.";

        }

}

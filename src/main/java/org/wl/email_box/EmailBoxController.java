package org.wl.email_box;

import User.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tool.CSVImporter;
import tool.VCardImporter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : EmailBoxController
 * @Description :邮箱界面
 * @Author : WL
 * @Date : 2024-04-27 15:45
 */
public class EmailBoxController {
    @FXML
    private Button addContactButton;

    @FXML
    private Button addGroupButton;

    @FXML
    private MenuItem allMenuItem;

    @FXML
    private TableColumn<Contact, String> birthdayTableColumn;

    @FXML
    private TableView<Contact> contactTableView;

    @FXML
    private Button deleteContactButton;

    @FXML
    private Button deleteGroupButton;

    @FXML
    private Button editContactButton;

    @FXML
    private TableColumn<Contact, String> emailTableColumn;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem exportMenuItem;

    @FXML
    private Menu fileMenu;

    @FXML
    private ListView<String> groupListView;

    @FXML
    private TableColumn<Contact, String> homeAddressTableColumn;

    @FXML
    private TableColumn<Contact, String> homepageTableColumn;

    @FXML
    private MenuItem importMenuItem;

    @FXML
    private VBox inputBox;

    @FXML
    private TableColumn<Contact, String> instantMessagingTableColumn;

    @FXML
    private Menu listMenu;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableColumn<Contact, String> mobileTableColumn;

    @FXML
    private TableColumn<Contact, String> nameTableColumn;

    @FXML
    private TableColumn<Contact, String> noteTableColumn;

    @FXML
    private TableColumn<Contact, String> photoTableColumn;

    @FXML
    private VBox searchBox;

    @FXML
    private Label searchLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<Contact, String> telephoneTableColumn;

    @FXML
    private MenuItem ungroupMenuItem;

    @FXML
    private TableColumn<Contact, String> workplaceTableColumn;

    @FXML
    private TableColumn<Contact, String> zipCodeTableColumn;

    private List<Contact> chosenContacts;                 //选中联系人,便于一些操作

    private User user;

    private ObservableList<Contact> data;

    public EmailBoxController(){
        user = new User();
        chosenContacts = new ArrayList<>();
        groupListView = new ListView<>();
        contactTableView = new TableView<>();
        groupListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        contactTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        groupListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setTableView(user.listFromGroup(newValue));
        });
    }

    @FXML
    void addContact(ActionEvent event) {
        //
        //
        //contact为创建新的联系人
        Contact contact = new Contact();
        if(user.addContact(contact)){
            setTableView(user.getAllContacts());
        }else{
            System.out.println("add error");
        }
    }

    @FXML
    void addGroup(ActionEvent event) {

    }

    @FXML
    void deleteContact(ActionEvent event) {

    }

    @FXML
    void deleteGroup(ActionEvent event) {

    }

    @FXML
    void editContact(ActionEvent event) {

    }

    @FXML
    void exitApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void exportFile(ActionEvent event) {

    }

    @FXML
    void importFile(ActionEvent event) {
        Stage primaryStage = (Stage) addContactButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            if(file.getName().toLowerCase().endsWith(".csv")){
                user = new User(CSVImporter.importContacts(file.getName()));
            } else if (file.getName().toLowerCase().endsWith(".vcf")) {
                user = new User(VCardImporter.importContacts(file.getName()));
            }else{
                System.out.println("file type error");
            }
            //更新界面
            for(Group group : user.getGroups()){
                groupListView.getItems().add(group.getName());
            }
            setTableView(user.getAllContacts());
        }
    }

    @FXML
    void listAll(ActionEvent event) {
        setTableView(user.getAllContacts());
    }

    @FXML
    void listUngroup(ActionEvent event) {
        setTableView(user.getOtherContacts());
    }

    void setTableView(List<Contact> contacts){
        data = FXCollections.observableList(contacts);
        contactTableView = new TableView<>(data);
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        telephoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        mobileTableColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        instantMessagingTableColumn.setCellValueFactory(new PropertyValueFactory<>("instantMessaging"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        homepageTableColumn.setCellValueFactory(new PropertyValueFactory<>("homepage"));
        birthdayTableColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        photoTableColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        workplaceTableColumn.setCellValueFactory(new PropertyValueFactory<>("workplace"));
        homeAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("homeAddress"));
        zipCodeTableColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        noteTableColumn.setCellValueFactory(new PropertyValueFactory<>("node"));
    }

}

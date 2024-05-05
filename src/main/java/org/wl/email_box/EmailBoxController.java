package org.wl.email_box;

import User.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tool.CSVExporter;
import tool.CSVImporter;
import tool.VCardExporter;
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
    private Button StartSearchButton;

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

    private List<Contact> chosenContacts;                    //选中联系人

    private String chosenGroupName;                             //选中组

    private User user;

    public EmailBoxController(){
        user = new User();
        chosenContacts = new ArrayList<>();
        groupListView = new ListView<>();
        contactTableView = new TableView<>();
        groupListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        contactTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        groupListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            chosenGroupName = newValue;
            setTableView(user.findGroup(chosenGroupName).getContacts());
        });
        contactTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue != null) {
                selectedContact(newValue);
            }
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
            System.out.println("add contact fail");
        }
    }

    @FXML
    void addGroup(ActionEvent event) {
        if(chosenContacts.isEmpty()){
            System.out.println("No selected contacts");
            return;
        }
        boolean flag = false;
        for(Contact contact : chosenContacts){
            if(!contact.getGroup().isEmpty()){
                flag = true;
                break;
            }
        }
        if(flag){
            System.out.println("selected contacts has group");
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("输⼊组名: ");
        dialog.showAndWait().ifPresentOrElse(
                result -> {
                    if(result.isEmpty()){
                        System.out.println("Null input");
                        return;
                    }
                    Group group = new Group(result);
                    for(Contact contact : chosenContacts){
                        group.addContact(contact);
                    }
                    if(user.addGroup(group)){
                        //刷新界面
                        setListView();
                        setTableView(user.getAllContacts());
                    }else{
                        System.out.println("add group fail");
                    }
                },
                () -> System.out.println("cancel")
        );
    }

    @FXML
    void deleteContact(ActionEvent event) {
        if(chosenContacts.isEmpty()){
            System.out.println("No select contact");
            return;
        }
        if(chosenContacts.size() == 1){
            Contact contact = chosenContacts.get(0);
            if(user.removeContact(contact)){
                //刷新界面
                setListView();
                setTableView(user.getAllContacts());
            }else {
                System.out.println("delete contact fail");
            }
        }else{
            System.out.println("select over one");
        }
    }

    @FXML
    void deleteGroup(ActionEvent event) {
        if(chosenGroupName.isEmpty()){
            System.out.println("No selected group");
            return;
        }
        if(user.removeGroup(user.findGroup(chosenGroupName))){
            //刷新界面
            setListView();
            setTableView(user.getAllContacts());
        } else {
            System.out.println("delete group fail");
        }
    }

    //编辑联系人
    //询问修改信息还是分组
    @FXML
    void editContact(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE, "编辑联系人信息or修改分组：",
                ButtonType.CANCEL,
                new ButtonType("Info", ButtonBar.ButtonData.OTHER),
                new ButtonType("Group", ButtonBar.ButtonData.OTHER)
        );
        alert.setTitle("编辑联系人"); // 设置对话框标题
        alert.showAndWait().ifPresent(response -> {
            if(response.getButtonData().isCancelButton()){
                alert.close();
            } else if (response.getText().equals("Info")) {
                editInfo();
            } else if (response.getText().equals("Group")) {
                editGroup();
            } else {
                System.out.println("choose error");
            }
        });
    }
    //修改信息
    private void editInfo(){

    }
    //调整分组
    private void editGroup(){
        if(chosenContacts.isEmpty()){
            System.out.println("No selected contact");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.NONE, "将联系人移出组or移到别的组：",
                ButtonType.CANCEL,
                new ButtonType("OutGroup", ButtonBar.ButtonData.OTHER),
                new ButtonType("ToGroup", ButtonBar.ButtonData.OTHER)
        );
        alert.setTitle("EditGroup");
        alert.showAndWait().ifPresent(response -> {
            if(response.getButtonData().isCancelButton()){
                alert.close();
            } else if (response.getText().equals("OutGroup")) {
                if(user.removeFromGroup(chosenContacts)){;
                    //刷新界面
                    setListView();
                    setTableView(user.getAllContacts());
                }
            } else if (response.getText().equals("ToGroup")) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText("（选中联系人将移到该组）输⼊组名：");
                dialog.showAndWait().ifPresentOrElse(
                        result -> {
                            if(result.isEmpty()){
                                System.out.println("Null input");
                                Group group = user.findGroup(result);
                                if(user.addToGroup(chosenContacts,group)){
                                    //刷新界面
                                    setListView();
                                    setTableView(user.getAllContacts());
                                }else{
                                    System.out.println("edit group fail");
                                }
                            }
                        },
                        () -> System.out.println("cancel")
                );
            } else {
                System.out.println("choose error");
            }
        });
    }

    @FXML
    void exitApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void exportFile(ActionEvent event) {
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("ALL","ALL","SELECTED");
        choiceDialog.setTitle("Export");
        choiceDialog.setHeaderText("选择要导出的联系人：");
        choiceDialog.showAndWait().ifPresentOrElse(
                result -> {
                    if(result.equals("ALL")){
                        exportFormChoose(user.getAllContacts());
                    } else if(result.equals("SELECTED")){
                        exportFormChoose(chosenContacts);
                    } else {System.out.println("type select error");}
                },
                ()->{System.out.println("cancel");}
        );
    }
    //选择导出文件格式
    private void exportFormChoose(List<Contact> contacts){
        if(contacts.isEmpty()){
            System.out.println("Contacts is empty");
            return;
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>("CSV","CSV","vCard");
        dialog.setTitle("Export");
        dialog.setHeaderText("选择导出文件格式：");
        dialog.showAndWait().ifPresentOrElse(
                result -> {
                    if(result.equals("CSV")){
                        CSVExporter.exportContacts(contacts);
                    } else if(result.equals("vCard")){
                        VCardExporter.exportContacts(contacts);
                    } else {System.out.println("type select error");}
                },
                () -> {System.out.println("no select");}
        );
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
            } else {
                System.out.println("file type error");
            }
            //更新界面
            setListView();
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

    private void setTableView(List<Contact> contacts){
        ObservableList<Contact> data = FXCollections.observableList(contacts);
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

    private void setListView(){
        for(Group group : user.getGroups()){
            groupListView.getItems().add(group.getName());
        }
    }

    private void selectedContact(Contact contact){
        chosenContacts = contactTableView.getSelectionModel().getSelectedItems();
    }


    //搜索
    @FXML
    void startSearch(ActionEvent event) {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Contact> contacts = contactTableView.getItems();
            contactTableView.getItems().clear();
            // 根据搜索关键字过滤联系人列表,可优化
            for (Contact contact : contacts) {
                if (contact.getName().toLowerCase().contains(newValue.toLowerCase())) {
                    contactTableView.getItems().add(contact);
                }
            }
        });
    }
}

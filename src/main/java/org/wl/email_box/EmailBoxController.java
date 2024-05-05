package org.wl.email_box;

import User.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
        //创建新窗口
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("输⼊你的姓名: ");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // 第一个文本框和标签
        Label nameLabel = new Label("name:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        // 第二个文本框和标签
        Label telephoneLabel = new Label("telephone:");
        TextField telephoneField = new TextField();
        gridPane.add(telephoneLabel, 0, 1);
        gridPane.add(telephoneField, 1, 1);

        Label mobileLabel = new Label("mobile:");
        TextField mobileField = new TextField();
        gridPane.add(mobileLabel, 0, 2);
        gridPane.add(mobileField, 1, 2);

        Label instantMessagingLabel = new Label("instantMessaging:");
        TextField instantMessagingField = new TextField();
        gridPane.add(instantMessagingLabel, 0, 3);
        gridPane.add(instantMessagingField, 1, 3);

        Label emailLabel = new Label("email:");
        TextField emailField = new TextField();
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailField, 1, 4);

        Label homepageLabel = new Label("homepage:");
        TextField homepageField = new TextField();
        gridPane.add(homepageLabel, 0, 5);
        gridPane.add(homepageField, 1, 5);

        Label birthdayLabel = new Label("birthday:");
        TextField birthdayField = new TextField();
        gridPane.add(birthdayLabel, 0, 6);
        gridPane.add(birthdayField, 1, 6);

        Label photoLabel = new Label("photo:");
        TextField photoField = new TextField();
        gridPane.add(photoLabel, 0, 7);
        gridPane.add(photoField, 1, 7);

        Label workplaceLabel = new Label("workplace:");
        TextField workplaceField = new TextField();
        gridPane.add(workplaceLabel, 0, 8);
        gridPane.add(workplaceField, 1, 8);

        Label homeAddressLabel = new Label("homeAddress:");
        TextField homeAddressField = new TextField();
        gridPane.add(homeAddressLabel, 0, 9);
        gridPane.add(homeAddressField, 1, 9);

        Label zipCodeLabel = new Label("zipCode:");
        TextField zipCodeField = new TextField();
        gridPane.add(zipCodeLabel, 0, 10);
        gridPane.add(zipCodeField, 1, 10);

        Label groupLabel = new Label("group:");
        TextField groupField = new TextField();
        gridPane.add(groupLabel, 0, 11);
        gridPane.add(groupField, 1, 11);

        Label noteLabel = new Label("note:");
        TextField noteField = new TextField();
        gridPane.add(noteLabel, 0, 12);
        gridPane.add(noteField, 1, 12);

        // 将GridPane添加到Dialog的内容中
        dialog.getDialogPane().setContent(gridPane);

        // 显示Dialog并等待用户的响应
        dialog.showAndWait().ifPresentOrElse(
                result -> System.out.println("姓名: " + nameField.getText() ),
                () -> System.out.println("选择了取消!")
        );

        Contact contact = new Contact(nameField.getText(),telephoneField.getText(),mobileField.getText(),
                instantMessagingField.getText(),emailField.getText(),homepageField.getText(),birthdayField.getText(),
                photoLabel.getText(),workplaceField.getText(),homeAddressField.getText(),zipCodeLabel.getText(),
                noteLabel.getText());
//        user.changeContact(contact,nameField.getText(),telephoneField.getText(),mobileField.getText(),
//                instantMessagingField.getText(),emailField.getText(),homepageField.getText(),birthdayField.getText(),
//                photoLabel.getText(),workplaceField.getText(),homeAddressField.getText(),zipCodeLabel.getText(),
//                noteLabel.getText());


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
        if(chosenContacts.isEmpty()){
            System.out.println("No selected contact");
            return;
        }
        if(chosenContacts.size()!=1){
            System.out.println("select over one");
            return ;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("输⼊你的姓名: ");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // 第一个文本框和标签
        Label nameLabel = new Label("name:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        // 第二个文本框和标签
        Label telephoneLabel = new Label("telephone:");
        TextField telephoneField = new TextField();
        gridPane.add(telephoneLabel, 0, 1);
        gridPane.add(telephoneField, 1, 1);

        Label mobileLabel = new Label("mobile:");
        TextField mobileField = new TextField();
        gridPane.add(mobileLabel, 0, 2);
        gridPane.add(mobileField, 1, 2);

        Label instantMessagingLabel = new Label("instantMessaging:");
        TextField instantMessagingField = new TextField();
        gridPane.add(instantMessagingLabel, 0, 3);
        gridPane.add(instantMessagingField, 1, 3);

        Label emailLabel = new Label("email:");
        TextField emailField = new TextField();
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailField, 1, 4);

        Label homepageLabel = new Label("homepage:");
        TextField homepageField = new TextField();
        gridPane.add(homepageLabel, 0, 5);
        gridPane.add(homepageField, 1, 5);

        Label birthdayLabel = new Label("birthday:");
        TextField birthdayField = new TextField();
        gridPane.add(birthdayLabel, 0, 6);
        gridPane.add(birthdayField, 1, 6);

        Label photoLabel = new Label("photo:");
        TextField photoField = new TextField();
        gridPane.add(photoLabel, 0, 7);
        gridPane.add(photoField, 1, 7);

        Label workplaceLabel = new Label("workplace:");
        TextField workplaceField = new TextField();
        gridPane.add(workplaceLabel, 0, 8);
        gridPane.add(workplaceField, 1, 8);

        Label homeAddressLabel = new Label("homeAddress:");
        TextField homeAddressField = new TextField();
        gridPane.add(homeAddressLabel, 0, 9);
        gridPane.add(homeAddressField, 1, 9);

        Label zipCodeLabel = new Label("zipCode:");
        TextField zipCodeField = new TextField();
        gridPane.add(zipCodeLabel, 0, 10);
        gridPane.add(zipCodeField, 1, 10);

        Label groupLabel = new Label("group:");
        TextField groupField = new TextField();
        gridPane.add(groupLabel, 0, 11);
        gridPane.add(groupField, 1, 11);

        Label noteLabel = new Label("note:");
        TextField noteField = new TextField();
        gridPane.add(noteLabel, 0, 12);
        gridPane.add(noteField, 1, 12);

        // 将GridPane添加到Dialog的内容中
        dialog.getDialogPane().setContent(gridPane);

        // 显示Dialog并等待用户的响应
        dialog.showAndWait().ifPresentOrElse(
                result -> System.out.println("姓名: " + nameField.getText() ),
                () -> System.out.println("选择了取消!")
        );

        user.changeContact(chosenContacts.get(0),nameField.getText(),telephoneField.getText(),mobileField.getText(),
                instantMessagingField.getText(),emailField.getText(),homepageField.getText(),birthdayField.getText(),
                photoLabel.getText(),workplaceField.getText(),homeAddressField.getText(),zipCodeLabel.getText(),
                noteLabel.getText());
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

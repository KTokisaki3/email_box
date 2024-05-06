package org.wl.email_box;

import User.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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

    @FXML
    private TableColumn<Contact,String> groupTableColumn;

    private List<Contact> chosenContacts = new ArrayList<>();                    //选中联系人

    private String chosenGroupName;                             //选中组

    private User user = new User();


    @FXML
    void addContact(ActionEvent event) {
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

        Label groupLabel1 = new Label("group:");
        Label groupLabel2 = new Label("新建联系人默认没有分组");
        gridPane.add(groupLabel1, 0, 11);
        gridPane.add(groupLabel2, 1, 11);

        Label noteLabel = new Label("note:");
        TextField noteField = new TextField();
        gridPane.add(noteLabel, 0, 12);
        gridPane.add(noteField, 1, 12);

        // 将GridPane添加到Dialog的内容中
        dialog.getDialogPane().setContent(gridPane);
        // 显示Dialog并等待用户的响应
        dialog.showAndWait().ifPresentOrElse(
                result ->  {
                    Contact contact = new Contact(nameField.getText(),telephoneField.getText(),mobileField.getText(),
                            instantMessagingField.getText(),emailField.getText(),homepageField.getText(),birthdayField.getText(),
                            photoField.getText(),workplaceField.getText(),homeAddressField.getText(),zipCodeField.getText(),
                            noteField.getText());
                    if(contact.isEmpty()){
                        System.out.println("Contact is empty");
                        return;
                    }
                    if(user.addContact(contact)){
                        System.out.println("add:" + contact);
                        //刷新界面
                        setTableView(user.getAllContacts());
                    }else{
                        System.out.println("add contact fail");
                    }
                },
                () -> {
                    System.out.println("cancel");
                    }
                );
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
                        System.out.println("add:" + group);
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
        for(Contact contact : chosenContacts){
            if(user.removeContact(contact)){
                System.out.println("delete:" + contact );
            }else {
                System.out.println("delete contact fail");
            }
        }
        //刷新界面
        setListView();
        setTableView(user.getAllContacts());
    }

    @FXML
    void deleteGroup(ActionEvent event) {
        if(chosenGroupName.isEmpty()){
            System.out.println("No selected group");
            return;
        }
        if(user.removeGroup(user.findGroup(chosenGroupName))){
            System.out.println("delete:" + chosenGroupName);
            chosenGroupName = "";
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


        Label noteLabel = new Label("note:");
        TextField noteField = new TextField();
        gridPane.add(noteLabel, 0, 11);
        gridPane.add(noteField, 1, 11);

        // 将GridPane添加到Dialog的内容中
        dialog.getDialogPane().setContent(gridPane);

        // 显示Dialog并等待用户的响应
        dialog.showAndWait().ifPresentOrElse(
                result -> {
                    Contact contact = new Contact(nameField.getText(),telephoneField.getText(),mobileField.getText(),
                            instantMessagingField.getText(),emailField.getText(),homepageField.getText(),birthdayField.getText(),
                            photoField.getText(),workplaceField.getText(),homeAddressField.getText(),zipCodeField.getText(),
                            noteField.getText());
                    if(contact.isEmpty()){
                        System.out.println("empty");
                        return;
                    }
                    user.changeContact(chosenContacts.get(0),nameField.getText(),telephoneField.getText(),mobileField.getText(),
                        instantMessagingField.getText(),emailField.getText(),homepageField.getText(),birthdayField.getText(),
                        photoField.getText(),workplaceField.getText(),homeAddressField.getText(),zipCodeField.getText(),
                        noteField.getText());
                    //刷新界面
                    setTableView(user.getAllContacts());
                    },
                () -> System.out.println("cancel")
        );
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
                if(user.removeFromGroup(chosenContacts)){
                    System.out.println("out group");
                    //刷新界面
                    setListView();
                    setTableView(user.getAllContacts());
                } else {
                    System.out.println("out group fail");
                }
            } else if (response.getText().equals("ToGroup")) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText("（选中联系人将移到该组）输⼊组名：");
                dialog.showAndWait().ifPresentOrElse(
                        result -> {
                            if(result.isEmpty()) {
                                System.out.println("Null input");
                            } else {
                                Group group = user.findGroup(result);
                                if(user.addToGroup(chosenContacts,group)){
                                    System.out.println("to group:" + group);
                                    //刷新界面
                                    setListView();
                                    setTableView(user.getAllContacts());
                                } else {
                                    System.out.println("to group fail");
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
        setListView();
        setTableView(user.getAllContacts());
    }

    @FXML
    void listUngroup(ActionEvent event) {
        setListView();
        setTableView(user.getOtherContacts());
    }

    private void setTableView(List<Contact> contacts){
        chosenContacts.clear();
        ObservableList<Contact> data = FXCollections.observableArrayList();
        data.addAll(contacts);
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
        groupTableColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        noteTableColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        contactTableView.setItems(data);
        contactTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        contactTableView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Contact>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    // 处理新添加的选定项
                    for(Contact contact : change.getAddedSubList()){
                        if(!chosenContacts.contains(contact)){
                            chosenContacts.add(contact);
                        }
                    }
                }
                if (change.wasRemoved()) {
                    // 处理被移除的选定项
                    for(Contact contact : change.getAddedSubList()){
                        chosenContacts.remove(contact);
                    }
                }
            }
        });
        contactTableView.refresh();
    }

    private void setListView(){
        chosenGroupName="";
        groupListView.getItems().clear();
        for(Group group : user.getGroups()){
            groupListView.getItems().add(group.getName());
        }
        groupListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        groupListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.isEmpty()){
                chosenGroupName = newValue;
                setTableView(user.findGroup(chosenGroupName).getContacts());
            }
        });
        groupListView.refresh();
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

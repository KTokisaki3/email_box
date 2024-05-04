package org.wl.email_box;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

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
    private TableColumn<?, ?> birthdayTabelColumn;

    @FXML
    private TableView<?> contactTableView;

    @FXML
    private Button deleteContactButton;

    @FXML
    private Button deleteGroupButton;

    @FXML
    private Button editContactButton;

    @FXML
    private TableColumn<?, ?> emailTabelColumn;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem exportMenuItem;

    @FXML
    private Menu fileMenu;

    @FXML
    private TreeView<?> groupTreeView;

    @FXML
    private TableColumn<?, ?> homeAddressTabelColumn;

    @FXML
    private TableColumn<?, ?> homepageTabelColumn;

    @FXML
    private MenuItem importMenuItem;

    @FXML
    private VBox inputBox;

    @FXML
    private TableColumn<?, ?> instantMessagingTabelColumn;

    @FXML
    private Menu listMenu;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableColumn<?, ?> mobileTabelColumn;

    @FXML
    private TableColumn<?, ?> nameTabelColumn;

    @FXML
    private TableColumn<?, ?> noteTabelColumn;

    @FXML
    private TableColumn<?, ?> photoTabelColumn;

    @FXML
    private VBox searchBox;

    @FXML
    private Label searchLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<?, ?> telephoneTabelColumn;

    @FXML
    private MenuItem ungroupMenuItem;

    @FXML
    private TableColumn<?, ?> workplaceTabelColumn;

    @FXML
    private TableColumn<?, ?> zipCodeTabelColumn;

    @FXML
    void addContact(ActionEvent event) {

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

    }

    @FXML
    void exportFile(ActionEvent event) {

    }

    @FXML
    void importFile(ActionEvent event) {

    }

    @FXML
    void listAll(ActionEvent event) {

    }

    @FXML
    void listUngroup(ActionEvent event) {

    }

}

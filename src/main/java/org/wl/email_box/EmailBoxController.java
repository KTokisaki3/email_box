package org.wl.email_box;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

/**
 * @ClassName : EmailBoxController
 * @Description :邮箱界面
 * @Author : WL
 * @Date : 2024-04-27 15:45
 */
public class EmailBoxController {
    @FXML
    private Button creat_contact_button;

    @FXML
    private Button creat_contact_group_button;

    @FXML
    private Button find_button;

    @FXML
    private TextField find_lxr_textfield;

    @FXML
    private VBox menubox;

    @FXML
    private ComboBox<?> more;

    @FXML
    private ToolBar tools;

    @FXML
    private VBox viewbox;

    @FXML
    void creatContact(ActionEvent event) {

    }

    @FXML
    void creatContactGroup(ActionEvent event) {

    }

    @FXML
    void findContact(ActionEvent event) {

    }
}

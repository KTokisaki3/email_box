module org.wl.email_box {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.wl.email_box to javafx.fxml;
    exports org.wl.email_box;
}
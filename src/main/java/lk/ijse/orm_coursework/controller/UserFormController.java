package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class UserFormController {

    public JFXTextField txtUserID;
    public JFXTextField txtUsername;
    public JFXTextField txtPassword1;
    public JFXPasswordField txtPassword2;
    public JFXTextField txtUserEmail;
    public JFXTextField txtRePassword1;
    public JFXPasswordField txtRePassword2;
    public TableView<?> tblUser;
    public TableColumn<?,?> colID;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colEmail;
    public TableColumn<?,?> colPassword;

    public void showPasswordOnMousePresseds(MouseEvent mouseEvent) {

    }

    public void showPasswordOnMouseReleased(MouseEvent mouseEvent) {

    }

    public void showPassword2OnMousePresseds(MouseEvent mouseEvent) {

    }

    public void showPassword2OnMouseReleased(MouseEvent mouseEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }
}

package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BOFactory;
import lk.ijse.orm_coursework.bo.Custom.UserBO;
import lk.ijse.orm_coursework.dao.Custom.UserDAO;
import lk.ijse.orm_coursework.dao.DAOFactory;
import lk.ijse.orm_coursework.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SigninFormController implements Initializable {
    @FXML
    public JFXTextField txtUsername;
    @FXML
    public JFXTextField txtPassword1;
    @FXML
    public JFXPasswordField txtPassword2;
    public AnchorPane SignInForm;
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPassword1.setVisible(false);
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword2.getText();

        if (userDAO.checkPassword(username,password)){
            Parent root = FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            SignInForm.getScene().getWindow().hide();
        }else {
            new Alert(Alert.AlertType.ERROR,"Please Check Username and password !!").show();
        }

        txtUsername.clear();
        txtPassword2.clear();
        txtPassword1.clear();
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SignUpForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        SignInForm.getScene().getWindow().hide();
    }

    public void showPasswordOnMousePresseds(MouseEvent mouseEvent) {
        txtPassword2.setVisible(false);
        txtPassword1.setText(txtPassword2.getText());
        txtPassword1.setVisible(true);
    }

    public void showPasswordOnMouseReleased(MouseEvent mouseEvent) {
        txtPassword2.setVisible(true);
        txtPassword1.setVisible(false);
    }
}
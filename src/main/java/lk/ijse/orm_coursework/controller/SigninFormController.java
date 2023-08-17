package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigninFormController implements Initializable {
    @FXML
    public JFXTextField txtUsername;
    @FXML
    public JFXTextField txtPassword1;
    @FXML
    public JFXPasswordField txtPassword2;
    public AnchorPane SignInForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPassword1.setVisible(false);
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {

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
package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
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
import lk.ijse.orm_coursework.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpFormController implements Initializable {
    public JFXTextField txtUsername;
    public JFXTextField txtPassword1;
    public JFXPasswordField txtPassword2;
    public AnchorPane SignUpForm;
    public JFXPasswordField txtRePassword2;
    public JFXTextField txtRePassword1;
    public JFXTextField txtUserID;
    public JFXTextField txtUserEmail;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPassword1.setVisible(false);
        txtRePassword1.setVisible(false);
    }

    public void showPasswordOnMousePresseds(MouseEvent mouseEvent) {
        txtPassword2.setVisible(false);
        txtPassword1.setText(txtPassword2.getText());
        txtPassword1.setVisible(true);
    }

    public void showPasswordOnMouseReleased(MouseEvent mouseEvent) {
        txtPassword1.setVisible(false);
        txtPassword2.setText(txtPassword1.getText());
        txtPassword2.setVisible(true);
    }

    public void showPassword2OnMousePresseds(MouseEvent mouseEvent) {
        txtRePassword2.setVisible(false);
        txtRePassword1.setText(txtRePassword2.getText());
        txtRePassword1.setVisible(true);
    }

    public void showPassword2OnMouseReleased(MouseEvent mouseEvent) {
        txtRePassword1.setVisible(false);
        txtRePassword2.setText(txtRePassword1.getText());
        txtRePassword2.setVisible(true);
    }

    public void lblHaveAccountOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SignInForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        SignUpForm.getScene().getWindow().hide();
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String id = txtUserID.getText();
        String username = txtUsername.getText();
        String password = txtRePassword2.getText();
        String email = txtUserEmail.getText();

        if (txtPassword2.getText().equalsIgnoreCase(txtRePassword2.getText())){

            System.out.println("Step 1");
            System.out.println(id);
            System.out.println(username);
            System.out.println(password);
            System.out.println(email);

            boolean isSaved = userBO.addUser(new UserDTO(id, username, password, email));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }
}

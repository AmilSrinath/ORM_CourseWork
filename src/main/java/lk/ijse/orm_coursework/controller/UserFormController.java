package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm_coursework.bo.BOFactory;
import lk.ijse.orm_coursework.bo.Custom.UserBO;
import lk.ijse.orm_coursework.dto.UserDTO;
import lk.ijse.orm_coursework.entity.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    public JFXTextField txtUserID;
    public JFXTextField txtUsername;
    public JFXTextField txtPassword1;
    public JFXPasswordField txtPassword2;
    public JFXTextField txtUserEmail;
    public JFXTextField txtRePassword1;
    public JFXPasswordField txtRePassword2;
    public TableView<User> tblUser;
    public TableColumn<?,?> colID;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colEmail;
    public TableColumn<?,?> colPassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    ObservableList<User> observableList;

    String ID;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setCellValueFactory();
        try {
            generateNextUserId();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        txtPassword1.setVisible(false);
        txtRePassword1.setVisible(false);
    }

    public void getAll() throws SQLException, ClassNotFoundException, IOException {
        observableList = FXCollections.observableArrayList();
        List<UserDTO> allUser = userBO.getAllUsers();

        for (UserDTO userDTO : allUser){
            observableList.add(new User(userDTO.getUserid(), userDTO.getName(), userDTO.getPassword(), userDTO.getEmail()));
        }
        tblUser.setItems(observableList);
    }

    void setCellValueFactory(){
        colID.setCellValueFactory(new PropertyValueFactory<>("userid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
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

    public void showPassword2OnMousePresseds(MouseEvent mouseEvent) {
        txtRePassword2.setVisible(false);
        txtRePassword1.setText(txtRePassword2.getText());
        txtRePassword1.setVisible(true);
    }

    public void showPassword2OnMouseReleased(MouseEvent mouseEvent) {
        txtRePassword2.setVisible(true);
        txtRePassword1.setVisible(false);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        String id = txtUserID.getText();
        String name = txtUsername.getText();
        String password = txtRePassword2.getText();
        String email = txtUserEmail.getText();

        if (password.equalsIgnoreCase(txtPassword2.getText())) {
            if (userBO.addUser(new UserDTO(id, name, password, email))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Don't match Passwords!!").show();
        }
        clearTextFileds();
        generateNextUserId();
        getAll();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!userBO.deleteUser(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextUserId();
        clearTextFileds();
        getAll();
    }

    public void clearTextFileds(){
        txtUsername.clear();
        txtPassword2.clear();
        txtRePassword2.clear();
        txtUserEmail.clear();
    }

    private void generateNextUserId() throws ClassNotFoundException {
        try {
            String nextId = userBO.generateNewUserID();
            txtUserID.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rowOnMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblUser.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        ID = colID.getCellData(index).toString();
        txtUsername.setText(colName.getCellData(index).toString());
        txtPassword2.setText(colPassword.getCellData(index).toString());
        txtRePassword2.setText(colPassword.getCellData(index).toString());
        txtUserEmail.setText(colEmail.getCellData(index).toString());


        System.out.println(ID);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearTextFileds();
    }
}

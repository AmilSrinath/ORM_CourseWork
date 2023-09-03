package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm_coursework.bo.BOFactory;
import lk.ijse.orm_coursework.bo.Custom.StudentBO;
import lk.ijse.orm_coursework.dto.StudentDTO;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    @FXML
    public DatePicker dataPickerDob;
    @FXML
    private JFXTextField txtStudentID;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private TableView<Student> tblStudent;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXComboBox<String> comGender;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    ObservableList<Student> observableList;
    String ID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Male", "Female");
        comGender.setItems(list);
        try {
            getAll();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
        try {
            generateNextUserId();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String id = txtStudentID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContactNo.getText();
        String dob = String.valueOf(dataPickerDob.getValue());
        String gender = comGender.getValue();

        if (studentBO.addStudent(new StudentDTO(id, name, address, contact, dob, gender))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }

        clearTextFileds();
        generateNextUserId();
        getAll();
    }

    private void clearTextFileds() {
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        dataPickerDob.setValue(null);
        comGender.setValue(null);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContactNo.getText();
        String dob = String.valueOf(dataPickerDob.getValue());
        String gender = comGender.getValue();

        if(studentBO.updateStudent(new StudentDTO(ID,name,address,contact,dob,gender, new ArrayList<>()))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearTextFileds();
        getAll();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!studentBO.deleteStudent(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextUserId();
        clearTextFileds();
        getAll();
    }

    @FXML
    void rowOnMouseClicked(MouseEvent event) {
        Integer index = tblStudent.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        ID = colID.getCellData(index).toString();

        txtName.setText(colName.getCellData(index).toString());
        txtContactNo.setText(colContact.getCellData(index).toString());
        txtAddress.setText(colAddress.getCellData(index).toString());
        dataPickerDob.setValue(LocalDate.parse(colDob.getCellData(index).toString()));
        comGender.setValue(colGender.getCellData(index).toString());
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFileds();
    }

    private void getAll() throws SQLException, IOException, ClassNotFoundException {
        observableList = FXCollections.observableArrayList();
        List<StudentDTO> allroom = studentBO.getAllStudent();

        for (StudentDTO studentDTO : allroom){
            observableList.add(new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender(), new ArrayList<Reservation>()));
        }
        tblStudent.setItems(observableList);
    }

    void setCellValueFactory(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void generateNextUserId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = studentBO.generateNewStudentID();
        txtStudentID.setText(nextId);
    }
}

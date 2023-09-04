package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm_coursework.bo.BOFactory;
import lk.ijse.orm_coursework.bo.Custom.ReservationBO;
import lk.ijse.orm_coursework.dao.Custom.ReservationDAO;
import lk.ijse.orm_coursework.dao.Custom.RoomDAO;
import lk.ijse.orm_coursework.dao.DAOFactory;
import lk.ijse.orm_coursework.dto.ReservationDTO;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.dto.StudentDTO;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;
import lk.ijse.orm_coursework.entity.tm.ReservationTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ReservationFormController implements Initializable {

    @FXML
    public AnchorPane ReservationForm;
    @FXML
    public JFXTextField txtStatus;
    public JFXTextField txtSearch;

    @FXML
    private JFXTextField txtReservationID;

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colRoomID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private JFXComboBox<String> comStudentID;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private JFXComboBox<String> comRoomID;
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    ObservableList<ReservationTM> observableList;
    String ID;

    Student student = new Student();
    Room room = new Room();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadStudentID();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            loadRoomID();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
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

        searchFilter();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFileds();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String id = txtReservationID.getText();
        String date = String.valueOf(dataPicker.getValue());
        String sid = comStudentID.getValue();
        String rid = comRoomID.getValue();
        String status = txtStatus.getText();

        roomDAO.updateRoomQut();

        student.setId(sid);
        room.setId(rid);

        if (reservationBO.addRevervation(new ReservationDTO(id, date, student, room, status))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }

        clearTextFileds();
        generateNextUserId();
        getAll();
        searchFilter();
    }

    private void getAll() throws SQLException, IOException, ClassNotFoundException {
        observableList = FXCollections.observableArrayList();
        List<ReservationDTO> allRevervation = reservationBO.getAllRevervation();

        for (ReservationDTO reservationDTO : allRevervation){
            observableList.add(new ReservationTM(
                    reservationDTO.getId(),
                    reservationDTO.getDate(),
                    reservationDTO.getStudent().getId(),
                    reservationDTO.getRoom().getId(),
                    reservationDTO.getStatus()));
        }
        tblReservation.setItems(observableList);
    }

    void setCellValueFactory(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("rid"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void generateNextUserId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = reservationBO.generateNewRevervationID();
        txtReservationID.setText(nextId);
    }

    private void clearTextFileds() {
        dataPicker.setValue(null);
        comRoomID.setValue(null);
        comStudentID.setValue(null);
        txtStatus.setText(null);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String date = String.valueOf(dataPicker.getValue());
        String sid = comStudentID.getValue();
        String rid = comRoomID.getValue();
        String status = txtStatus.getText();

        student.setId(sid);
        room.setId(rid);

        if(reservationBO.updateRevervation(new ReservationDTO(ID,date,student,room,status))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }

        clearTextFileds();
        getAll();
        searchFilter();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!reservationBO.deleteRevervation(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextUserId();
        clearTextFileds();
        getAll();
        searchFilter();
    }

    @FXML
    void rowOnMouseClicked(MouseEvent event) {
        Integer index = tblReservation.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        ID = colID.getCellData(index).toString();
        dataPicker.setValue(LocalDate.parse(colDate.getCellData(index).toString()));
        comStudentID.setValue(colStudentID.getCellData(index).toString());
        comRoomID.setValue(colRoomID.getCellData(index).toString());
        txtStatus.setText(colStatus.getCellData(index).toString());
    }

    private void loadStudentID() throws SQLException, IOException {
        List<String> id = reservationDAO.loadStudentID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        comStudentID.setItems(obList);
    }

    private void loadRoomID() throws SQLException, IOException {
        List<String> id = reservationDAO.loadRoomID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        comRoomID.setItems(obList);
    }

    private void searchFilter(){
        FilteredList<ReservationTM> filterData = new FilteredList<>(observableList, e -> true);
        txtSearch.setOnKeyReleased(e->{
            txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super ReservationTM>) reservation->{
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();
                    if (reservation.getId().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(reservation.getStatus().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(reservation.getSid().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(reservation.getRid().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(reservation.getDate().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }
                    return false;
                });
            });

            SortedList<ReservationTM> buyer = new SortedList<>(filterData);
            buyer.comparatorProperty().bind(tblReservation.comparatorProperty());
            tblReservation.setItems(buyer);
        });
    }
}

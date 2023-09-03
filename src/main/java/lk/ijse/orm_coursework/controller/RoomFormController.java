package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm_coursework.bo.BOFactory;
import lk.ijse.orm_coursework.bo.Custom.RoomBO;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {

    public JFXTextField txtRoomID;
    public JFXComboBox<String> comRoomType;
    public JFXTextField txtKeymoney;
    public JFXTextField txtRoomQut;
    public TableView<Room> tblRoom;
    public TableColumn<?,?> colID;
    public TableColumn<?,?> colRoomType;
    public TableColumn<?,?> colKeyMoney;
    public TableColumn<?,?> colRoomQun;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    ObservableList<Room> observableList;
    String ID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Non-AC", "Non-AC / Food", "AC", "AC / Food");
        comRoomType.setItems(list);
        try {
            generateNextUserId();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            getAll();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        String id = txtRoomID.getText();
        String type = comRoomType.getValue();
        double keyMoney = Double.parseDouble(txtKeymoney.getText());
        int roomQut = Integer.parseInt(txtRoomQut.getText());

        if (roomBO.addRoom(new RoomDTO(id, type, keyMoney, roomQut))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearTextFileds();
        generateNextUserId();
        getAll();
    }

    private void getAll() throws SQLException, IOException, ClassNotFoundException {
        observableList = FXCollections.observableArrayList();
        List<RoomDTO> allroom = roomBO.getAllRooms();

        for (RoomDTO roomDTO : allroom){
            observableList.add(new Room(roomDTO.getId(), roomDTO.getType(), roomDTO.getKeymoney(), roomDTO.getQuntity(), new ArrayList<Reservation>()));
        }
        tblRoom.setItems(observableList);
    }

    void setCellValueFactory(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keymoney"));
        colRoomQun.setCellValueFactory(new PropertyValueFactory<>("quntity"));
    }

    private void generateNextUserId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = roomBO.generateNewRoomID();
        txtRoomID.setText(nextId);
    }

    private void clearTextFileds() {
        colKeyMoney.setStyle("");
        txtKeymoney.clear();
        txtRoomQut.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        double keyMoney = Double.parseDouble(txtKeymoney.getText());
        int qut = Integer.parseInt(txtRoomQut.getText());
        String type = comRoomType.getValue();

        if(roomBO.updateRoom(new RoomDTO(ID,type,keyMoney,qut))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }

        clearTextFileds();
        getAll();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!roomBO.deleteRoom(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextUserId();
        clearTextFileds();
        getAll();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearTextFileds();
    }

    public void rowOnMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblRoom.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        ID = colID.getCellData(index).toString();
        txtRoomQut.setText(colRoomQun.getCellData(index).toString());
        txtKeymoney.setText(colKeyMoney.getCellData(index).toString());
        comRoomType.setValue(colRoomType.getCellData(index).toString());
    }
}

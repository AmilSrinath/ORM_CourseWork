package lk.ijse.orm_coursework.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm_coursework.bo.BOFactory;
import lk.ijse.orm_coursework.bo.Custom.RoomBO;
import lk.ijse.orm_coursework.bo.Custom.UserBO;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.dto.UserDTO;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
            observableList.add(new Room(roomDTO.getId(), roomDTO.getType(), roomDTO.getKeymoney(), roomDTO.getQuntity()));
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

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

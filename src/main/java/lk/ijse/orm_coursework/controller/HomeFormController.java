package lk.ijse.orm_coursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HomeFormController {

    public ImageView UserImg;
    public ImageView RoomImg;
    public ImageView StudentImg;
    public StackPane ControllArea;

    public void btnUserOnMouseEntered(MouseEvent mouseEvent) {
        Image image = new Image(getClass().getResource("/assest/userIcon2.png").toExternalForm());
        UserImg.setImage(image);
    }

    public void btnUserOnMouseExited(MouseEvent mouseEvent) {
        Image image = new Image(getClass().getResource("/assest/userIcon1.png").toExternalForm());
        UserImg.setImage(image);
    }

    public void btnRoomOnMouseEntered(MouseEvent mouseEvent) {
        Image image = new Image(getClass().getResource("/assest/room2.png").toExternalForm());
        RoomImg.setImage(image);
    }

    public void btnRoomOnMouseExited(MouseEvent mouseEvent) {
        Image image = new Image(getClass().getResource("/assest/room1.png").toExternalForm());
        RoomImg.setImage(image);
    }

    public void btnStudentOnMouseEntered(MouseEvent mouseEvent) {
        Image image = new Image(getClass().getResource("/assest/student2.png").toExternalForm());
        StudentImg.setImage(image);
    }

    public void btnStudentOnMouseExited(MouseEvent mouseEvent) {
        Image image = new Image(getClass().getResource("/assest/student1.png").toExternalForm());
        StudentImg.setImage(image);
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/UserForm.fxml"));
        AnchorPane anchorPane = loader.load();
        ControllArea.getChildren().removeAll();
        ControllArea.getChildren().setAll(anchorPane);
    }

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/RoomForm.fxml"));
        AnchorPane anchorPane = loader.load();
        ControllArea.getChildren().removeAll();
        ControllArea.getChildren().setAll(anchorPane);
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/StudentForm.fxml"));
        AnchorPane anchorPane = loader.load();
        ControllArea.getChildren().removeAll();
        ControllArea.getChildren().setAll(anchorPane);
    }
}

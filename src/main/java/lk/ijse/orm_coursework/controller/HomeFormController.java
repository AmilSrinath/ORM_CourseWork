package lk.ijse.orm_coursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {

    public ImageView UserImg;
    public ImageView RoomImg;
    public ImageView StudentImg;
    public Button btnUser;
    public Button btnRoom;
    public Button btnStudent;
    public Label lblD24;
    public Label lblWelcome;
    public AnchorPane ControllArea;

    int user=0;
    int room=0;
    int student=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void btnUserOnMouseEntered(MouseEvent mouseEvent) {
        if (user==0) {
            Image image = new Image(getClass().getResource("/assest/userIcon2.png").toExternalForm());
            UserImg.setImage(image);
            btnUser.setStyle("-fx-background-color: black;-fx-text-fill: white");
        }
    }

    public void btnUserOnMouseExited(MouseEvent mouseEvent) {
        if (user==0) {
            Image image = new Image(getClass().getResource("/assest/userIcon1.png").toExternalForm());
            UserImg.setImage(image);
            btnUser.setStyle("-fx-background-color: transparent;-fx-text-fill: black");
        }
    }

    public void btnRoomOnMouseEntered(MouseEvent mouseEvent) {
        if (room==0) {
            Image image = new Image(getClass().getResource("/assest/room2.png").toExternalForm());
            RoomImg.setImage(image);
            btnRoom.setStyle("-fx-background-color: black;-fx-text-fill: white");
        }
    }

    public void btnRoomOnMouseExited(MouseEvent mouseEvent) {
        if (room==0) {
            Image image = new Image(getClass().getResource("/assest/room1.png").toExternalForm());
            RoomImg.setImage(image);
            btnRoom.setStyle("-fx-background-color: transparent;-fx-text-fill: black");
        }
    }

    public void btnStudentOnMouseEntered(MouseEvent mouseEvent) {
        if (student==0) {
            Image image = new Image(getClass().getResource("/assest/student2.png").toExternalForm());
            StudentImg.setImage(image);
            btnStudent.setStyle("-fx-background-color: black;-fx-text-fill: white");
        }
    }

    public void btnStudentOnMouseExited(MouseEvent mouseEvent) {
        if (student==0) {
            Image image = new Image(getClass().getResource("/assest/student1.png").toExternalForm());
            StudentImg.setImage(image);
            btnStudent.setStyle("-fx-background-color: transparent;-fx-text-fill: black");
        }
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

    public void btnUserOnMouseClicked(MouseEvent mouseEvent) {
        Image studentImg = new Image(getClass().getResource("/assest/student1.png").toExternalForm());
        StudentImg.setImage(studentImg);
        btnStudent.setStyle("-fx-background-color: transparent;-fx-text-fill: black");

        Image roomImg = new Image(getClass().getResource("/assest/room1.png").toExternalForm());
        RoomImg.setImage(roomImg);
        btnRoom.setStyle("-fx-background-color: transparent;-fx-text-fill: black");

        Image image1 = new Image(getClass().getResource("/assest/userIcon2.png").toExternalForm());
        UserImg.setImage(image1);
        btnUser.setStyle("-fx-background-color: black;-fx-text-fill: white");
        user++;
        room=0;
        student=0;
    }

    public void btnRoomOnMouseClicked(MouseEvent mouseEvent) {
        Image userImg = new Image(getClass().getResource("/assest/userIcon1.png").toExternalForm());
        UserImg.setImage(userImg);
        btnUser.setStyle("-fx-background-color: transparent;-fx-text-fill: black");

        Image studentImg = new Image(getClass().getResource("/assest/student1.png").toExternalForm());
        StudentImg.setImage(studentImg);
        btnStudent.setStyle("-fx-background-color: transparent;-fx-text-fill: black");

        Image roomImg = new Image(getClass().getResource("/assest/room2.png").toExternalForm());
        RoomImg.setImage(roomImg);
        btnRoom.setStyle("-fx-background-color: black;-fx-text-fill: white");
        room++;
        user=0;
        student=0;
    }

    public void btnStudentOnMouseClicked(MouseEvent mouseEvent) {
        Image roomImg = new Image(getClass().getResource("/assest/room1.png").toExternalForm());
        RoomImg.setImage(roomImg);
        btnRoom.setStyle("-fx-background-color: transparent;-fx-text-fill: black");

        Image userImg = new Image(getClass().getResource("/assest/userIcon1.png").toExternalForm());
        UserImg.setImage(userImg);
        btnUser.setStyle("-fx-background-color: transparent;-fx-text-fill: black");

        Image studentImg = new Image(getClass().getResource("/assest/student2.png").toExternalForm());
        StudentImg.setImage(studentImg);
        btnStudent.setStyle("-fx-background-color: black;-fx-text-fill: white");
        student++;
        user=0;
        room=0;
    }
}

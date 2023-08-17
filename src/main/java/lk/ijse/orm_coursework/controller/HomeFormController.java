package lk.ijse.orm_coursework.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomeFormController {

    public ImageView UserImg;
    public ImageView RoomImg;

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
}

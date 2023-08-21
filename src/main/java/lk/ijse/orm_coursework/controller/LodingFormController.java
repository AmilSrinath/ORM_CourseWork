package lk.ijse.orm_coursework.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.orm_coursework.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LodingFormController implements Initializable {

    public AnchorPane LodingForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(8000), actionEvent -> {
            Session session = null;
            try {
                session = FactoryConfiguration.getInstance().getSession();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            session.close();
        });
        timeline.getKeyFrames().addAll(keyFrame1);
        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            try {
                LodingForm.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/view/SignInForm.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

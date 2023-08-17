module lk.ijse.orm_coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;

    opens lk.ijse.orm_coursework to javafx.fxml;
    exports lk.ijse.orm_coursework;
    exports lk.ijse.orm_coursework.controller;
    opens lk.ijse.orm_coursework.controller to javafx.fxml;
}
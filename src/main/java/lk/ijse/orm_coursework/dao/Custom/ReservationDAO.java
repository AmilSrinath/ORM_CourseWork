package lk.ijse.orm_coursework.dao.Custom;

import lk.ijse.orm_coursework.dao.CrudDAO;
import lk.ijse.orm_coursework.entity.Reservation;

import java.io.IOException;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation> {
    List<String> loadStudentID() throws IOException;
    List<String> loadRoomID() throws IOException;
}

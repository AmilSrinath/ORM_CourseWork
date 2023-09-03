package lk.ijse.orm_coursework.dao.Custom;

import lk.ijse.orm_coursework.dao.CrudDAO;
import lk.ijse.orm_coursework.entity.Room;

import java.io.IOException;

public interface RoomDAO extends CrudDAO<Room> {
    void updateRoomQut() throws IOException;
}

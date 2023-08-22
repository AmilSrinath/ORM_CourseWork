package lk.ijse.orm_coursework.bo.Custom;

import lk.ijse.orm_coursework.bo.SuperBO;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    public List<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException, IOException;
    public boolean addRoom(RoomDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException, IOException;

    public String generateNewRoomID() throws SQLException, ClassNotFoundException, IOException;
}

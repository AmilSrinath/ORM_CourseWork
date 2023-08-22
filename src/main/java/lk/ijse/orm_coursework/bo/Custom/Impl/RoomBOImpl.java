package lk.ijse.orm_coursework.bo.Custom.Impl;

import lk.ijse.orm_coursework.bo.Custom.RoomBO;
import lk.ijse.orm_coursework.dao.Custom.RoomDAO;
import lk.ijse.orm_coursework.dao.DAOFactory;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.dto.UserDTO;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public List<RoomDTO> getAllRooms() throws SQLException, IOException, ClassNotFoundException {
        List<RoomDTO> allRooms= new ArrayList<>();
        List<Room> all = roomDAO.getAll();
        for (Room room : all) {
            allRooms.add(new RoomDTO(room.getId(), room.getType(), room.getKeymoney(), room.getQuntity()));
        }
        return allRooms;
    }

    @Override
    public boolean addRoom(RoomDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.add(new Room(dto.getId(), dto.getType(), dto.getKeymoney(), dto.getQuntity()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws SQLException, IOException, ClassNotFoundException {
        return roomDAO.update(new Room(dto.getId(), dto.getType(), dto.getKeymoney(), dto.getQuntity()));
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, IOException, ClassNotFoundException {
        return roomDAO.delete(id);
    }

    @Override
    public String generateNewRoomID() throws SQLException, IOException, ClassNotFoundException {
        return roomDAO.generateNewID();
    }
}

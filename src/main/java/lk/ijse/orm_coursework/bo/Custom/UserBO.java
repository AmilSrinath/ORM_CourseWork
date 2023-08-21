package lk.ijse.orm_coursework.bo.Custom;

import lk.ijse.orm_coursework.bo.SuperBO;
import lk.ijse.orm_coursework.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException, IOException;
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException;

    public String generateNewUserID() throws SQLException, ClassNotFoundException;
}

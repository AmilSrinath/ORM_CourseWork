package lk.ijse.orm_coursework.bo.Custom.Impl;

import lk.ijse.orm_coursework.bo.Custom.UserBO;
import lk.ijse.orm_coursework.dao.Custom.UserDAO;
import lk.ijse.orm_coursework.dao.DAOFactory;
import lk.ijse.orm_coursework.dto.UserDTO;
import lk.ijse.orm_coursework.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.add(new User(dto.getUserid(), dto.getName(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewUserID() throws SQLException, ClassNotFoundException {
        return null;
    }
}

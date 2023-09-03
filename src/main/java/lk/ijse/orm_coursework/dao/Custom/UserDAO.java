package lk.ijse.orm_coursework.dao.Custom;

import lk.ijse.orm_coursework.dao.CrudDAO;
import lk.ijse.orm_coursework.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    boolean checkPassword(String username, String password) throws IOException;
}

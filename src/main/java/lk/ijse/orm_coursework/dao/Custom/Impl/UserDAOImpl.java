package lk.ijse.orm_coursework.dao.Custom.Impl;

import lk.ijse.orm_coursework.dao.Custom.UserDAO;
import lk.ijse.orm_coursework.entity.User;
import lk.ijse.orm_coursework.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(User entity) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}

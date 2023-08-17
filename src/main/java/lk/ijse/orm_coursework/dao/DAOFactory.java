package lk.ijse.orm_coursework.dao;

import lk.ijse.orm_coursework.dao.Custom.Impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}

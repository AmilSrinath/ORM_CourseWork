package lk.ijse.orm_coursework.dao;

import lk.ijse.orm_coursework.dao.Custom.Impl.RoomDAOImpl;
import lk.ijse.orm_coursework.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.orm_coursework.dao.Custom.Impl.UserDAOImpl;

import static lk.ijse.orm_coursework.bo.BOFactory.BOTypes.ROOM;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,ROOM,STUDENT
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            default:
                return null;
        }
    }
}

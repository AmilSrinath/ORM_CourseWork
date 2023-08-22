package lk.ijse.orm_coursework.dao.Custom.Impl;

import lk.ijse.orm_coursework.dao.Custom.RoomDAO;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.User;
import lk.ijse.orm_coursework.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public List<Room> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM room");
        nativeQuery.addEntity(Room.class);
        List<Room> rooms = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return rooms;
    }

    @Override
    public boolean add(Room entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM room ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        System.out.println(id);

        if(id != null){
            String[] strings = id.split("RM-");
            int newID = Integer.parseInt(strings[1]);
            newID++;
            String ID = String.valueOf(newID);
            int length = ID.length();
            if (length < 2){
                return "RM-00"+newID;
            }else {
                if (length < 3){
                    return "RM-0"+newID;
                }else {
                    return "RM"+newID;
                }
            }
        }else {
            return "RM-001";
        }
    }
}

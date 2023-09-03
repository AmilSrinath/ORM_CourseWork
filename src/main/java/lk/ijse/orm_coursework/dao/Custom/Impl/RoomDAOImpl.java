package lk.ijse.orm_coursework.dao.Custom.Impl;

import lk.ijse.orm_coursework.dao.Custom.RoomDAO;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.User;
import lk.ijse.orm_coursework.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
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
    public boolean update(Room entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DELETE FROM room WHERE id = :id";
        NativeQuery<User> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.setParameter("id",id);
        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM room ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

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

    @Override
    public void updateRoomQut() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "UPDATE room SET quntity = (quntity - 1)";
        NativeQuery<User> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();
    }
}

package lk.ijse.orm_coursework.bo.Custom.Impl;

import lk.ijse.orm_coursework.bo.Custom.ReservationBO;
import lk.ijse.orm_coursework.dao.Custom.ReservationDAO;
import lk.ijse.orm_coursework.dao.Custom.RoomDAO;
import lk.ijse.orm_coursework.dao.DAOFactory;
import lk.ijse.orm_coursework.dto.ReservationDTO;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public List<ReservationDTO> getAllRevervation() throws SQLException, ClassNotFoundException, IOException {
        List<ReservationDTO> allReservation= new ArrayList<>();
        List<Reservation> all = reservationDAO.getAll();
        for (Reservation reservation : all) {
            allReservation.add(new ReservationDTO(reservation.getId(),reservation.getDate(),reservation.getStudent(),reservation.getRoom(),reservation.getStatus()));
        }
        return allReservation;
    }

    @Override
    public boolean addRevervation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.add(new Reservation(dto.getId(), dto.getDate(), dto.getStudent(), dto.getRoom(), dto.getStatus()));
    }

    @Override
    public boolean updateRevervation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.update(new Reservation(dto.getId(), dto.getDate(), dto.getStudent(), dto.getRoom(), dto.getStatus()));
    }

    @Override
    public boolean deleteRevervation(String id) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.delete(id);
    }

    @Override
    public String generateNewRevervationID() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.generateNewID();
    }
}

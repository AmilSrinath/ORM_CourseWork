package lk.ijse.orm_coursework.bo.Custom;

import lk.ijse.orm_coursework.bo.SuperBO;
import lk.ijse.orm_coursework.dto.ReservationDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {
    public List<ReservationDTO> getAllRevervation() throws SQLException, ClassNotFoundException, IOException;
    public boolean addRevervation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean updateRevervation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean deleteRevervation(String id) throws SQLException, ClassNotFoundException, IOException;

    public String generateNewRevervationID() throws SQLException, ClassNotFoundException, IOException;
}

package lk.ijse.orm_coursework.bo.Custom;

import lk.ijse.orm_coursework.bo.SuperBO;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException, IOException;
    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException, IOException;

    public String generateNewStudentID() throws SQLException, ClassNotFoundException, IOException;
}

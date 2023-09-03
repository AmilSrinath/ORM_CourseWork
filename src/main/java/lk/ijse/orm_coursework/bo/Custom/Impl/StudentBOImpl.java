package lk.ijse.orm_coursework.bo.Custom.Impl;

import lk.ijse.orm_coursework.bo.Custom.StudentBO;
import lk.ijse.orm_coursework.dao.Custom.RoomDAO;
import lk.ijse.orm_coursework.dao.Custom.StudentDAO;
import lk.ijse.orm_coursework.dao.DAOFactory;
import lk.ijse.orm_coursework.dto.RoomDTO;
import lk.ijse.orm_coursework.dto.StudentDTO;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException, IOException {
        List<StudentDTO> allStudent= new ArrayList<>();
        List<Student> all = studentDAO.getAll();
        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getContact(), student.getDob(), student.getGender(), student.getReservations()));
        }
        return allStudent;
    }

    @Override
    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.add(new Student(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDob(), dto.getGender(), new ArrayList<Reservation>()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.update(new Student(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDob(), dto.getGender(), new ArrayList<Reservation>()));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.delete(id);
    }

    @Override
    public String generateNewStudentID() throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.generateNewID();
    }
}

package lk.ijse.orm_coursework.dto;

import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ReservationDTO {
    private String id;
    private String date;
    private Student student;
    private Room room;
    private String status;
}

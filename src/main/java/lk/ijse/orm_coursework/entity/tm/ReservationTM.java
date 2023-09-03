package lk.ijse.orm_coursework.entity.tm;

import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ReservationTM {
    private String id;
    private String date;
    private String sid;
    private String rid;
    private String status;
}

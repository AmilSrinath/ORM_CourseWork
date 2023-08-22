package lk.ijse.orm_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Reservation {
    @Id
    private String id;
    private String date;
    private String sid;
    private String rid;
    private String status;
}

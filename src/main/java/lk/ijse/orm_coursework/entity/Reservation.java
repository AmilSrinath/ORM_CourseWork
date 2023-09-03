package lk.ijse.orm_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private Student student;
    @ManyToOne
    private Room room;
    private String status;
}

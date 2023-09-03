package lk.ijse.orm_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Room {
    @Id
    private String id;
    private String type;
    private double keymoney;
    private int quntity;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
}

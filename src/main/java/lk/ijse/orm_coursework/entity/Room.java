package lk.ijse.orm_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Room {
    @Id
    private String id;
    private String type;
    private double keymoney;
    private int quntity;
}

package lk.ijse.orm_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    @OneToMany(mappedBy = "student")
    private List<Reservation> reservations;
}

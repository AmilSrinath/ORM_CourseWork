package lk.ijse.orm_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String address;
    private String contact;
    private String dob;
    private String gender;
}

package lk.ijse.orm_coursework.dto;

import lk.ijse.orm_coursework.entity.Reservation;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class StudentDTO {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    private List<Reservation> reservations;

    public StudentDTO(String id, String name, String address, String contact, String dob, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }
}

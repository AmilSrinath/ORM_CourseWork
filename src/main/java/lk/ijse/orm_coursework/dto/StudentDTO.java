package lk.ijse.orm_coursework.dto;

import lombok.*;

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
}

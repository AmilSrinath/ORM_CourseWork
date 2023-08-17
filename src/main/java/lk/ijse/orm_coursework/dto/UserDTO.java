package lk.ijse.orm_coursework.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class UserDTO {
    private String userid;
    private String name;
    private String password;
    private String email;
}

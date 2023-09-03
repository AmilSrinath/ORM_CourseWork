package lk.ijse.orm_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

@Entity
public class User {
    @Id
    private String userid;
    private String username;
    private String password;
    private String email;
}

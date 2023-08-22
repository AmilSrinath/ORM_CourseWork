package lk.ijse.orm_coursework.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

public class RoomDTO {
    private String id;
    private String type;
    private double keymoney;
    private int quntity;
}

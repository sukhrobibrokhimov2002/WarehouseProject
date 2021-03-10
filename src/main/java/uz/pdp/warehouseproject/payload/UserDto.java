package uz.pdp.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer code;
    private String password;
    List<Integer> wareHouseId;
}

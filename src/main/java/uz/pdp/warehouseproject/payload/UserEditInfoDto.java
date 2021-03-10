package uz.pdp.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditInfoDto {

    private String firstName;
    private String lastName;
    private String password;
}

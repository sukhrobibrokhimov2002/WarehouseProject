package uz.pdp.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneNumberDto {
    private Integer code;
    private String phoneNumber;
}

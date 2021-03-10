package uz.pdp.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {


    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;

}

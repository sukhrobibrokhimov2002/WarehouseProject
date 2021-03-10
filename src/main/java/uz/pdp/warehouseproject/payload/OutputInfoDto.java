package uz.pdp.warehouseproject.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OutputInfoDto {
    private Date date;
    private Integer wareHouseId;
    private Integer currencyId;
    private String factureNumber;
    private Integer clientId;
}

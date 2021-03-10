package uz.pdp.warehouseproject.payload;

import lombok.Data;

import java.util.Currency;
import java.util.Date;

@Data
public class OutputDto {
    private Integer wareHouseId;
    private Integer currencyId;
    private String factureNumber;
    private Integer clientId;
}

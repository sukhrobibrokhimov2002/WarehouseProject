package uz.pdp.warehouseproject.payload;

import lombok.Data;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.entity.Product;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class InputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Date expireDate;
    private Integer inputId;


}

package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    @ManyToOne
    private WareHouse wareHouse;
    @ManyToOne
    private Currency currency;
    private String factureNumber;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    private Client client;

}

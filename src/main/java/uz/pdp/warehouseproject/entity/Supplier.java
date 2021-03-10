package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.warehouseproject.entity.template.AbsEntity;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier extends AbsEntity {

    private String phoneNumber;
}

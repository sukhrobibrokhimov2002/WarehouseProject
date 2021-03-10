package uz.pdp.warehouseproject.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.warehouseproject.entity.template.AbsEntity;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class WareHouse  extends AbsEntity {

}

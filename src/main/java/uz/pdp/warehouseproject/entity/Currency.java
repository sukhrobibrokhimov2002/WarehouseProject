package uz.pdp.warehouseproject.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.warehouseproject.entity.template.AbsEntity;

import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {

}

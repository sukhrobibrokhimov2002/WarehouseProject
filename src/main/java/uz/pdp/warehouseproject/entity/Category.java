package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.warehouseproject.entity.template.AbsEntity;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsEntity {
    @ManyToOne
    private Category parentCategory;

}

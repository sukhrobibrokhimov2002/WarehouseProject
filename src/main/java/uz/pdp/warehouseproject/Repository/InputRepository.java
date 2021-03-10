package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.entity.Input;

import java.util.List;
import java.util.Optional;

public interface InputRepository extends JpaRepository<Input,Integer> {

    boolean existsByCode(String code);
    List<Input> findByWareHouse_Id(Integer wareHouse_id);
    List<Input> findBySupplier_Id(Integer supplier_id);
    List<Input> findByCurrency_Id(Integer currency_id);

}

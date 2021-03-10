package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Output;

import java.util.List;

public interface OutputRepository extends JpaRepository<Output,Integer> {

boolean existsByCode(String code);
List<Output> findAllByClient_Id(Integer client_id);
List<Output> findAllByCurrency_Id(Integer currency_id);
List<Output> findAllByWareHouse_Id(Integer wareHouse_id);
}

package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.InputProduct;

import java.util.List;
import java.util.Optional;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
List<InputProduct> findAllByInput_Id(Integer input_id);
List<InputProduct> findAllByProduct_Id(Integer product_id);

Optional<InputProduct> findByInput_Id(Integer input_id);

}

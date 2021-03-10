package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByCode(String code);
    List<Product> findByCategory_Id(Integer category_id);
}

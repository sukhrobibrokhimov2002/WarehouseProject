package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {

    boolean existsByName(String name);
}

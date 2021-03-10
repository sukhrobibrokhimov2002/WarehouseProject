package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);
}

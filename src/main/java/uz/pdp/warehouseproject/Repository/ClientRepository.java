package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}

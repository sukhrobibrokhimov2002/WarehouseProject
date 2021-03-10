package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.entity.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByCode(int code);

    @Query(value = "select * from users where users.id in(select users_id from users_ware_houses where ware_houses_id=:warehouseId)",nativeQuery = true)
    List<Users> getAllByWarehouseId(Integer warehouseId);


}

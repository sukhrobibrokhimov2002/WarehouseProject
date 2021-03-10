package uz.pdp.warehouseproject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

   List<Category> findAllByParentCategoryId(Integer parentCategory_id);
   boolean existsByName(String name);
   boolean existsByParentCategoryId(Integer parentCategory_id);

}

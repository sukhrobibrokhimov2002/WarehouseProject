package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.CategoryRepository;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.payload.CategoryDto;
import uz.pdp.warehouseproject.payload.Result;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result add(CategoryDto categoryDto) {

        if (categoryDto.getName() == null) return new Result("Please enter category name", false);
        boolean checkName = categoryRepository.existsByName(categoryDto.getName());
        if (checkName == true) return new Result("Category already exist", false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!parentCategory.isPresent()) return new Result("Parent category not found", false);
            category.setParentCategory(parentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Successfully added", true);
    }


    //getting all by parent category id
    public List<Category> getByParentId(Integer parendId) {
        List<Category> allByParentCategoryId = categoryRepository.findAllByParentCategoryId(parendId);
        return allByParentCategoryId;
    }


    public List<Category> getAll() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    public Result delete(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) return new Result("Category not found", false);

        boolean checkParent = categoryRepository.existsByParentCategoryId(id);
        if (checkParent == true) return new Result("This category is father of another", false);

        categoryRepository.deleteById(id);
        return new Result("Successfully deleted", true);

    }

    public Result edit(Integer id, CategoryDto categoryDto) {
        if (categoryDto.getName() == null) return new Result("Please enter category name", false);
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) return new Result("Category not found", false);
        Category category = categoryOptional.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) return new Result("Parent category not found", false);
            category.setParentCategory(optionalParentCategory.get());
        }

        categoryRepository.save(category);
        return new Result("Successfully edited", true);


    }
}

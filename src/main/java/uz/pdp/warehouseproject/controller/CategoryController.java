package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.payload.CategoryDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/byParentId/{id}")
    public List<Category> getAllByParentId(@PathVariable Integer id) {
        List<Category> byParentId = categoryService.getByParentId(id);
        return byParentId;
    }

    @GetMapping
    public List<Category> getAll() {
        List<Category> all = categoryService.getAll();
        return all;

    }

    @PostMapping
    public Result add(@RequestBody CategoryDto categoryDto) {
        Result add = categoryService.add(categoryDto);
        return add;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = categoryService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        Result edit = categoryService.edit(id, categoryDto);
        return edit;
    }
}

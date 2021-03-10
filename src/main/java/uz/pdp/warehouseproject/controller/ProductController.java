package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.payload.ProductDto;
import uz.pdp.warehouseproject.payload.ProductInfoDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Result add(@RequestBody ProductDto productDto) {
        Result add = productService.add(productDto);
        return add;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result result = productService.deleteById(id);
        return result;
    }

    @GetMapping
    public Page<Product> getAll(@RequestParam Integer page) {
        Page<Product> all = productService.getAll(page);
        return all;
    }

    @GetMapping("byCategoryId/{categoryId}")
    public List<Product> getByCategoryId(@PathVariable Integer categoryId) {
        List<Product> byCategoryId = productService.getByCategoryId(categoryId);
        return byCategoryId;
    }

    @PutMapping("/editInfo/{id}")
    public Result editInfo(@PathVariable Integer id, @RequestBody ProductInfoDto productInfoDto) {
        Result result = productService.editInfo(id, productInfoDto);
        return result;
    }


}

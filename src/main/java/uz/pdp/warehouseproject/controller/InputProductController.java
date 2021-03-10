package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.InputProduct;
import uz.pdp.warehouseproject.payload.InputProductDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto) {
        Result add = inputProductService.add(inputProductDto);
        return add;
    }


    @GetMapping
    public Page<InputProduct> getAll(@RequestParam Integer page) {
        Page<InputProduct> all = inputProductService.getAll(page);
        return all;
    }

    @GetMapping("/byProductId/{productId}")
    public List<InputProduct> getByProductId(@PathVariable Integer productId) {
        List<InputProduct> byProduct = inputProductService.getByProduct(productId);
        return byProduct;
    }

    @GetMapping("/byInputId/{inputId}")
    public List<InputProduct> getByInputId(@PathVariable Integer inputId) {
        List<InputProduct> byInput = inputProductService.getByInput(inputId);
        return byInput;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = inputProductService.delete(id);
        return delete;
    }

    @PutMapping("/edit/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        Result edit = inputProductService.edit(id, inputProductDto);
        return edit;
    }
}

package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.OutputProduct;
import uz.pdp.warehouseproject.payload.OutputProductDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto) {
        Result add = outputProductService.add(outputProductDto);
        return add;
    }

    @GetMapping
    public Page<OutputProduct> getAll(@RequestParam Integer page) {
        Page<OutputProduct> all = outputProductService.getAll(page);
        return all;
    }

    @GetMapping("/byOutput/{outputId}")
    public List<OutputProduct> getByOutput(@PathVariable Integer outputId) {
        List<OutputProduct> byOutputId = outputProductService.getByOutputId(outputId);
        return byOutputId;
    }

    @GetMapping("/byProductId/{productId}")
    public List<OutputProduct> getByProduct(@PathVariable Integer productId) {
        List<OutputProduct> byProductId = outputProductService.getByProductId(productId);
        return byProductId;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = outputProductService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        Result edit = outputProductService.edit(id, outputProductDto);
        return edit;
    }
}

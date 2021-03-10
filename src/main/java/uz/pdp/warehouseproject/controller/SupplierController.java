package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result add(@RequestBody Supplier supplier) {
        Result add = supplierService.add(supplier);
        return add;
    }

    @GetMapping
    public Page<Supplier> getAll(@RequestParam Integer page) {
        Page<Supplier> all = supplierService.getAll(page);
        return all;
    }

    @GetMapping("/{id}")
    public Supplier getOneById(@PathVariable Integer id) {
        Supplier oneById = supplierService.getOneById(id);
        return oneById;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = supplierService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Supplier supplier) {
        Result edit = supplierService.edit(id, supplier);
        return edit;
    }

}

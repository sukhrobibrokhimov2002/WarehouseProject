package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.WareHouse;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.WareHouseService;

@RestController
@RequestMapping("/wareHouse")
public class WarehouseController {

    @Autowired
    WareHouseService wareHouseService;

    @PostMapping
    public Result add(@RequestBody WareHouse wareHouse) {
        Result add = wareHouseService.add(wareHouse);
        return add;
    }

    @GetMapping
    public Page<WareHouse> getAll(@RequestParam Integer page) {
        Page<WareHouse> all = wareHouseService.getAll(page);
        return all;
    }

    @GetMapping("/{id}")
    public WareHouse getOneById(@PathVariable Integer id) {
        WareHouse oneById = wareHouseService.getOneById(id);
        return oneById;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = wareHouseService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody WareHouse wareHouse) {
        Result edit = wareHouseService.edit(id, wareHouse);
        return edit;
    }

}

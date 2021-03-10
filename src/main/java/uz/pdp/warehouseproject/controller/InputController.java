package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.payload.InputDto;
import uz.pdp.warehouseproject.payload.InputInfoDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;


    @PostMapping
    public Result add(@RequestBody InputDto inputDto) {
        Result add = inputService.add(inputDto);
        return add;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = inputService.delete(id);
        return delete;
    }

    @GetMapping()
    public Page<Input> getAll(@RequestParam Integer page) {
        Page<Input> all = inputService.getAll(page);
        return all;
    }

    @GetMapping("/byCurrency/{currencyId}")
    public List<Input> getByCurrencyId(@PathVariable Integer currencyId) {
        List<Input> byCurrencyId = inputService.getByCurrencyId(currencyId);
        return byCurrencyId;
    }

    @GetMapping("/bySupplierId/{supplierId}")
    public List<Input> getBySupplierId(@PathVariable Integer supplierId) {
        List<Input> bySupplierId = inputService.getBySupplierId(supplierId);
        return bySupplierId;
    }

    @GetMapping("/byWarehouseId/{warehouseId}")
    public List<Input> getByWarehouseId(@PathVariable Integer warehouseId) {
        List<Input> byWarehouseId = inputService.getByWarehouseId(warehouseId);
        return byWarehouseId;
    }

    @GetMapping("/{id}")
    public Input getOneById(@PathVariable Integer id) {
        Input oneById = inputService.getOneById(id);
        return oneById;
    }

    @PutMapping("/{id}")
    public Result editInfo(@PathVariable Integer id,@RequestBody InputInfoDto inputInfoDto) {
        Result edit = inputService.editInfo(id, inputInfoDto);
        return edit;
    }


}

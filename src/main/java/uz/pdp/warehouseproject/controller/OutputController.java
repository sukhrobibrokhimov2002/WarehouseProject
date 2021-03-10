package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Output;
import uz.pdp.warehouseproject.payload.OutputDto;
import uz.pdp.warehouseproject.payload.OutputInfoDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public Result add(@RequestBody OutputDto outputDto) {
        Result add = outputService.add(outputDto);
        return add;
    }

    @GetMapping
    public Page<Output> getAll(@RequestParam Integer page) {
        Page<Output> all = outputService.getAll(page);
        return all;
    }

    @GetMapping("byClient/{clientId}")
    public List<Output> getByClient(@PathVariable Integer clientId) {
        List<Output> byClientId = outputService.getByClientId(clientId);
        return byClientId;
    }

    @GetMapping("/bywarehouse/{warehouseId}")
    public List<Output> getByWareHouse(@PathVariable Integer warehouseId) {
        List<Output> byWareHouseId = outputService.getByWareHouseId(warehouseId);
        return byWareHouseId;
    }

    @GetMapping("/byCurrency/{currencyId}")
    public List<Output> getByCurrencyId(@PathVariable Integer currencyId) {
        List<Output> byCurrency = outputService.getByCurrency(currencyId);
        return byCurrency;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = outputService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputInfoDto outputInfoDto) {
        Result result = outputService.editInfo(id, outputInfoDto);
        return result;
    }
}

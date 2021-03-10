package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {
        Result result = measurementService.addMeasurement(measurement);
        return result;
    }

    @GetMapping
    public List<Measurement> getAll() {
        List<Measurement> all = measurementService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Measurement getOne(@PathVariable Integer id) {
        Measurement oneById = measurementService.getOneById(id);
        return oneById;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = measurementService.delete(id);
        return delete;
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Measurement measurement){
        Result edit = measurementService.edit(measurement, id);
        return edit;
    }
}

package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.MeasurementRepository;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.payload.Result;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurement(Measurement measurement) {
        if(measurement.getName()==null) return new Result("Name can't be empty",false);


        boolean checkMeasurement = measurementRepository.existsByName(measurement.getName());
        if (checkMeasurement) return new Result("Measurement exist", false);
        measurementRepository.save(measurement);
        return new Result("Successfully added", true);
    }

    public List<Measurement> getAll() {
        List<Measurement> all = measurementRepository.findAll();

        return all;
    }

    public Measurement getOneById(Integer id) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (!measurementOptional.isPresent()) return new Measurement();

        return measurementOptional.get();
    }

    public Result delete(Integer id) {
        try {
            Optional<Measurement> measurementOptional = measurementRepository.findById(id);
            if (!measurementOptional.isPresent()) return new Result("Measurement not found", false);
            measurementRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        } catch (Exception e) {
            return new Result("Error in deleting", false);
        }
    }

    public Result edit(Measurement measurement, Integer id) {
        if(measurement.getName()==null) return new Result("Name can't be empty",false);

        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (!measurementOptional.isPresent()) return new Result("Measurement not found", false);
        Measurement measurement1 = measurementOptional.get();
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new Result("Successfully edited", true);

    }
}

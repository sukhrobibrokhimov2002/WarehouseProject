package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.SupplierRepository;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.payload.Result;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result add(Supplier supplier) {
        if(supplier.getName()==null) return new Result("Name can't be empty",false);

        boolean checkSupplier = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (checkSupplier) return new Result("This supplier already exists", false);
        supplierRepository.save(supplier);
        return new Result("Successfully added", true);
    }

    public Page<Supplier> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Supplier> all = supplierRepository.findAll(pageable);
        return all;
    }

    public Supplier getOneById(Integer id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (!supplierOptional.isPresent()) return new Supplier();

        return supplierOptional.get();
    }

    public Result delete(Integer id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (!supplierOptional.isPresent()) return new Result("Supplier not found", false);
        supplierRepository.deleteById(id);
        return new Result("Supplier successfully deleted", true);
    }

    public Result edit(Integer id, Supplier supplier) {
        if(supplier.getName()==null) return new Result("Name can't be empty",false);

        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (!supplierOptional.isPresent()) return new Result("Supplier not found", false);

        boolean checkPhone = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (checkPhone) return new Result("This user already exists", true);

        Supplier supplier1 = supplierOptional.get();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Successfully edited", true);


    }
}

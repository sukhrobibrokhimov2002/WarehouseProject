package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.*;
import uz.pdp.warehouseproject.entity.*;
import uz.pdp.warehouseproject.payload.InputDto;
import uz.pdp.warehouseproject.payload.InputInfoDto;
import uz.pdp.warehouseproject.payload.Result;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    public Result add(InputDto inputDto) {
        Optional<Currency> currencyOptional = currencyRepository.findById(inputDto.getCurrencyId());
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(inputDto.getWarehouseId());

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());

        if (!optionalSupplier.isPresent() || !optionalWareHouse.isPresent() || !currencyOptional.isPresent())
            return new Result("Error", false);
        Input input = new Input();
        input.setCode(String.valueOf(UUID.randomUUID()));
        input.setCurrency(currencyOptional.get());
        input.setDate(new Date());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setSupplier(optionalSupplier.get());
        input.setWareHouse(optionalWareHouse.get());
        inputRepository.save(input);
        return new Result("Successfully saved", true);
    }

    public Page<Input> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Input> all = inputRepository.findAll(pageable);
        return all;
    }

    public Input getOneById(Integer id) {
        Optional<Input> byId = inputRepository.findById(id);
        if (!byId.isPresent()) return new Input();
        return byId.get();
    }

    //Getting inputs by warehouse
    public List<Input> getByWarehouseId(Integer warehouseId) {
        Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(warehouseId);
        if (!wareHouseOptional.isPresent()) return null;
        List<Input> byWareHouse_id = inputRepository.findByWareHouse_Id(warehouseId);
        return byWareHouse_id;
    }

    //getting inputs by supplier id
    public List<Input> getBySupplierId(Integer supplierId) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent()) return null;
        List<Input> bySupplier_id = inputRepository.findBySupplier_Id(supplierId);
        return bySupplier_id;
    }

    //getting inputs by currency id
    public List<Input> getByCurrencyId(Integer currencyId) {
        Optional<Currency> currencyOptional = currencyRepository.findById(currencyId);
        if (!currencyOptional.isPresent()) return null;
        List<Input> byCurrency_id = inputRepository.findByCurrency_Id(currencyId);
        return byCurrency_id;
    }


    public Result delete(Integer id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        if (!inputOptional.isPresent()) return new Result("Input not found", false);
        Optional<InputProduct> byInput_id = inputProductRepository.findByInput_Id(id);

        //Input va input produktlar foreign key vilan bog'langan va inputproductga berilayotgan input berilganmi yuqmi tekshirilayapti
        if(byInput_id.isPresent()) return new Result("First of all, you must delete products under this input",false);

        inputRepository.deleteById(id);
        return new Result("Successfully deleted", true);
    }

    public Result editInfo(Integer id, InputInfoDto inputInfoDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return new Result("Input not found", false);

        Optional<Currency> currencyOptional = currencyRepository.findById(inputInfoDto.getCurrencyId());
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(inputInfoDto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputInfoDto.getSupplierId());

        if (!optionalSupplier.isPresent() || !optionalWareHouse.isPresent() || !currencyOptional.isPresent())
            return new Result("Error", false);

        Input input = optionalInput.get();
        input.setWareHouse(optionalWareHouse.get());
        input.setDate(new Date());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(currencyOptional.get());
        input.setFactureNumber(inputInfoDto.getFactureNumber());
        inputRepository.save(input);
        return new Result("Successfully edited", true);


    }


}

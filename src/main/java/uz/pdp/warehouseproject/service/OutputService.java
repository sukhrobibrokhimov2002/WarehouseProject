package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.*;
import uz.pdp.warehouseproject.entity.*;
import uz.pdp.warehouseproject.payload.OutputDto;
import uz.pdp.warehouseproject.payload.OutputInfoDto;
import uz.pdp.warehouseproject.payload.Result;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    OutputProductRepository outputProductRepository;

    public Result add(OutputDto outputDto) {
        Optional<Client> clientOptional = clientRepository.findById(outputDto.getClientId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(outputDto.getWareHouseId());

        if (!clientOptional.isPresent() || !optionalWareHouse.isPresent() || !optionalCurrency.isPresent())
            return new Result("Error", false);


        Output output = new Output();
        output.setCode(String.valueOf(UUID.randomUUID()));
        output.setCurrency(optionalCurrency.get());
        output.setDate(new Date());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWareHouse(optionalWareHouse.get());
        output.setClient(clientOptional.get());
        outputRepository.save(output);
        return new Result("Successfully added", true);

    }

    public Page<Output> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Output> all = outputRepository.findAll(pageable);
        return all;
    }

    public List<Output> getByClientId(Integer clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent()) return null;

        List<Output> allByClient_id = outputRepository.findAllByClient_Id(clientId);
        return allByClient_id;
    }

    public List<Output> getByWareHouseId(Integer wareHouseId) {
        Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(wareHouseId);
        if (!wareHouseOptional.isPresent()) return null;


        List<Output> allByWareHouse_id = outputRepository.findAllByWareHouse_Id(wareHouseId);
        return allByWareHouse_id;
    }

    public List<Output> getByCurrency(Integer currencyId) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (!optionalCurrency.isPresent()) return null;

        List<Output> allByCurrency_id = outputRepository.findAllByCurrency_Id(currencyId);
        return allByCurrency_id;
    }

    public Result delete(Integer id) {

        Optional<OutputProduct> byOutput_id = outputProductRepository.findByOutput_Id(id);
        if (!byOutput_id.isPresent()) return new Result("Error. THis class have a child class", false);

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new Result("Output not found", false);


        outputRepository.deleteById(id);
        return new Result("Successfully deleted", true);

    }

    public Result editInfo(Integer id, OutputInfoDto outputInfoDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new Result("Output not found", false);

        Optional<Client> clientOptional = clientRepository.findById(outputInfoDto.getClientId());

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputInfoDto.getCurrencyId());
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(outputInfoDto.getWareHouseId());

        if (!clientOptional.isPresent() || !optionalWareHouse.isPresent() || !optionalCurrency.isPresent())
            return new Result("Error", false);

        Output output = optionalOutput.get();
        output.setClient(clientOptional.get());
        output.setDate(outputInfoDto.getDate());
        output.setWareHouse(optionalWareHouse.get());
        output.setFactureNumber(outputInfoDto.getFactureNumber());
        output.setClient(clientOptional.get());
        outputRepository.save(output);
        return new Result("Successfully deleted", true);
    }

}

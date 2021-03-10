package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.CurrencyRepository;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.payload.Result;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(Currency currency) {
        if(currency.getName()==null) return new Result("Please enter currency name",false);
        boolean checkCurrency = currencyRepository.existsByName(currency.getName());
        if (checkCurrency) return new Result("This currenct is already existed", false);
        currencyRepository.save(currency);
        return new Result("Successfully added", true);
    }

    public Page<Currency> getAllCurrencies(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Currency> all = currencyRepository.findAll(pageable);
        return all;
    }

    public Currency getOneById(Integer id) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (!currencyOptional.isPresent()) return new Currency();

        return currencyOptional.get();
    }

    public Result delete(Integer id) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (!currencyOptional.isPresent()) return new Result("Currency not found", false);
        currencyRepository.deleteById(id);
        return new Result("Successfully deleted", true);
    }

    public Result edit(Integer id, Currency currency) {
        if(currency.getName()==null) return new Result("Please enter currency name",false);

        Optional<Currency> currencyOptional = currencyRepository.findById(id);

        if (!currencyOptional.isPresent()) return new Result("Currency not found", false);
        boolean checkCurrency = currencyRepository.existsByName(currency.getName());

        if (checkCurrency) return new Result("This currency is already existed", false);
        Currency editedCurrency = currencyOptional.get();
        editedCurrency.setName(currency.getName());
        currencyRepository.save(editedCurrency);
        return new Result("Currency successfully edited", true);
    }
}

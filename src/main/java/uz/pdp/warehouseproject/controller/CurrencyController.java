package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result add(@RequestBody Currency currency) {
        Result add = currencyService.add(currency);
        return add;
    }

    @GetMapping
    public Page<Currency> getAll(@RequestParam Integer page) {
        Page<Currency> allCurrencies = currencyService.getAllCurrencies(page);
        return allCurrencies;
    }

    @GetMapping("/{id}")
    public Currency getOneById(@PathVariable Integer id) {
        Currency oneById = currencyService.getOneById(id);
        return oneById;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = currencyService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Currency currency) {
        Result edit = currencyService.edit(id, currency);
        return edit;
    }
}

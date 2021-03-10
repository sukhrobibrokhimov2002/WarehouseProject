package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Client;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    ClientService clientService;

    @PostMapping
    public Result add(@RequestBody Client client) {
        Result add = clientService.add(client);
        return add;
    }

    @GetMapping
    public Page<Client> getAll(@RequestParam Integer page) {

        Page<Client> all = clientService.getAll(page);
        return all;
    }

    @GetMapping("/{id}")
    public Client getOneById(@PathVariable Integer id) {
        Client oneById = clientService.getOneById(id);
        return oneById;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = clientService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Client client) {
        Result edit = clientService.edit(id, client);
        return edit;
    }
}

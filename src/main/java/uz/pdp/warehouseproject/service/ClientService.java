package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.ClientRepository;
import uz.pdp.warehouseproject.entity.Client;
import uz.pdp.warehouseproject.payload.Result;

import java.util.Optional;

@Service
public class ClientService {


    @Autowired
    ClientRepository clientRepository;

    public Result add(Client client) {
        boolean checkClient = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (checkClient) return new Result("Client  already exists in database", false);
        if (client.getName() == null) return new Result("name can't be empty", false);
        clientRepository.save(client);
        return new Result("Client successfully added", true);
    }

    public Page<Client> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Client> all = clientRepository.findAll(pageable);
        return all;
    }

    public Client getOneById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) return new Client();
        return clientOptional.get();
    }

    public Result delete(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) return new Result("Client not found",false);
        clientRepository.deleteById(id);
        return new Result("Client successfully deleted",true);
    }

    public Result edit(Integer id,Client client){
        boolean checkClient = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (checkClient) return new Result("Client  already exists in database", false);
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) return new Result("Client not found",false);
        //checking name is null or not
        if(client.getName()==null) return new Result("name can't be empty",false);
        Client client1 = clientOptional.get();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Successfully edited",true);
    }
}

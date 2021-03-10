package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.WareHouseRepository;
import uz.pdp.warehouseproject.entity.WareHouse;
import uz.pdp.warehouseproject.payload.Result;

import java.util.Optional;

@Service
public class WareHouseService {
    @Autowired
    WareHouseRepository wareHouseRepository;


    public Result add(WareHouse wareHouse) {
        if(wareHouse.getName()==null) return new Result("Name can't be empty",false);

        boolean checkWareHouse = wareHouseRepository.existsByName(wareHouse.getName());
        if (checkWareHouse) return new Result("This warehouse is already existed", false);
        wareHouseRepository.save(wareHouse);
        return new Result("WareHouse successfully added", true);
    }

    public Page<WareHouse> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<WareHouse> all = wareHouseRepository.findAll(pageable);
        return all;
    }

    public WareHouse getOneById(Integer id) {
        Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(id);
        if (!wareHouseOptional.isPresent()) return new WareHouse();
        return wareHouseOptional.get();
    }

    public Result delete(Integer id) {
        Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(id);
        if (!wareHouseOptional.isPresent()) return new Result("WareHouse Not found", false);
        wareHouseRepository.deleteById(id);
        return new Result("Successfully deleted", true);
    }

    public Result edit(Integer id, WareHouse wareHouse) {
        if(wareHouse.getName()==null) return new Result("Name can't be empty",false);

        Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(id);

        if (!wareHouseOptional.isPresent()) return new Result("WareHouse Not found", false);
        boolean checkWareHouse= wareHouseRepository.existsByName(wareHouse.getName());

        if(checkWareHouse) return new Result("This warehouse is already existed",false);
        WareHouse wareHouse1 = wareHouseOptional.get();
        wareHouse1.setName(wareHouse.getName());
        wareHouseRepository.save(wareHouse1);
        return new Result("WareHouse successfully edited",true);


    }
}

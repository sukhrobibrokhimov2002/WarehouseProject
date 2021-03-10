package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.UserRepository;
import uz.pdp.warehouseproject.Repository.WareHouseRepository;
import uz.pdp.warehouseproject.entity.Users;
import uz.pdp.warehouseproject.entity.WareHouse;
import uz.pdp.warehouseproject.payload.*;

import java.util.*;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    WareHouseRepository wareHouseRepository;

    public Result add(UserDto userDto) {
        Set<WareHouse> wareHouseSet = new HashSet<>();
        try {
            List<Integer> wareHouseId = userDto.getWareHouseId();

            for (Integer wareId : wareHouseId) {

                Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(wareId);
                if (!wareHouseOptional.isPresent()) return new Result("WareHouse not found", false);
                wareHouseSet.add(wareHouseOptional.get());
            }
            boolean checkCode = userRepository.existsByCode(userDto.getCode());
            boolean checkPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
            if (checkPhoneNumber == true && checkCode == true) {
                return new Result("User already exist", false);
            }
            Users users = new Users();
            users.setFirstName(userDto.getFirstName());
            users.setCode(userDto.getCode());
            users.setPassword(userDto.getPassword());
            users.setLastName(userDto.getLastName());
            users.setPhoneNumber(userDto.getPhoneNumber());
            users.setWareHouses(wareHouseSet);
            userRepository.save(users);
            return new Result("User Successfully added", true);

        } catch (Exception e) {
            return new Result("Error in adding user", false);
        }

    }

    public Page<Users> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Users> all = userRepository.findAll(pageable);
        return all;
    }

    public List<Users> getUsersByWarehouseI(Integer warehouseId) {
        List<Users> allByWarehouseId = userRepository.getAllByWarehouseId(warehouseId);
        return allByWarehouseId;
    }

    public Result delete(Integer id) {
        Optional<Users> usersOptional = userRepository.findById(id);
        if (!usersOptional.isPresent()) return new Result("User not found", false);
        userRepository.deleteById(id);
        return new Result("Successfully deleted", true);
    }

    public Result editInfo(Integer id, UserEditInfoDto userDto) {
        Optional<Users> usersOptional = userRepository.findById(id);
        if (!usersOptional.isPresent()) return new Result("User not found", false);
//checking user's phone number amd phoneNumber
        Users users = usersOptional.get();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setPassword(userDto.getPassword());
        return new Result("Successfully edited", true);
    }

    public Result editUsersWarehouse(Integer id, UserEditWareHouseDto userEditWareHouseDto) {
        Optional<Users> usersOptional = userRepository.findById(id);
        if (!usersOptional.isPresent()) return new Result("User not found", false);
        Set<WareHouse> editedWareHouseSet = new HashSet<>();
        for (Integer warehouseId : userEditWareHouseDto.getWareHouseId()) {
            Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(warehouseId);
            if (!wareHouseOptional.isPresent()) return new Result("Warehouse not found", false);
            editedWareHouseSet.add(wareHouseOptional.get());
        }
        Users users = usersOptional.get();
        users.setWareHouses(editedWareHouseSet);
        userRepository.save(users);
        return new Result("User wareHouses successfully edited", true);

    }

    public Result changePhoneNumber(Integer id, UserPhoneNumberDto userPhoneNumberDto) {
        Optional<Users> usersOptional = userRepository.findById(id);
        if (!usersOptional.isPresent()) return new Result("User not found", false);


        boolean checkNumber = userRepository.existsByPhoneNumber(userPhoneNumberDto.getPhoneNumber());
        boolean checkCode = userRepository.existsByCode(userPhoneNumberDto.getCode());
        if (checkCode || checkNumber) return new Result("Error. code or phone number already exist in database", false);
        Users users = usersOptional.get();
        users.setPhoneNumber(userPhoneNumberDto.getPhoneNumber());
        users.setCode(userPhoneNumberDto.getCode());
        userRepository.save(users);
        return new Result("Phone number and code successfully changed", true);

    }

}

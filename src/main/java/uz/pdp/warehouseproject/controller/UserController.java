package uz.pdp.warehouseproject.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Users;
import uz.pdp.warehouseproject.payload.*;
import uz.pdp.warehouseproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result add(@RequestBody UserDto userDto) {
        Result add = userService.add(userDto);
        return add;
    }

    @GetMapping
    public Page<Users> getAll(@RequestParam Integer page) {
        Page<Users> all = userService.getAll(page);
        return all;
    }

    @GetMapping("/bywarehouseId/{wareHouseId}")
    public List<Users> getByWareHouseId(@PathVariable Integer wareHouseId) {
        List<Users> usersByWarehouseI = userService.getUsersByWarehouseI(wareHouseId);
        return usersByWarehouseI;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = userService.delete(id);
        return delete;
    }

    @PutMapping("/editUserPerInfo/{id}")
    public Result editInfo(@PathVariable Integer id, @RequestBody UserEditInfoDto editInfoDto) {
        Result edit = userService.editInfo(id, editInfoDto);
        return edit;
    }

    @PutMapping("/editWarehouse/{id}")
    public Result editWarehouse(@PathVariable Integer id, @RequestBody UserEditWareHouseDto userEditWareHouseDto) {
        Result result = userService.editUsersWarehouse(id, userEditWareHouseDto);

        return result;
    }

    @PutMapping("/changePhone/{id}")
    public Result changePhone(@PathVariable Integer id, @RequestBody UserPhoneNumberDto userPhoneNumberDto) {
        Result result = userService.changePhoneNumber(id, userPhoneNumberDto);
        return result;
    }
}

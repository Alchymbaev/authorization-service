package kg.megacom.authorizationservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.authorizationservice.models.dtos.UsersDto;
import kg.megacom.authorizationservice.models.entities.Users;
import kg.megacom.authorizationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Пользователь")
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/save")
    @ApiOperation("Сохранение")
    UsersDto save(@RequestBody UsersDto usersDto){
        return service.save(usersDto);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск пользователя по ID")
    UsersDto findById(@RequestParam(required = false) Long id){
        return service.findById(id);
    }

    @GetMapping("/findAll")
    @ApiOperation("Выыод всех пользователей")
    List<UsersDto> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    UsersDto delete(@RequestParam(required = false) Long id) {
        return service.delete(id);
    }
}

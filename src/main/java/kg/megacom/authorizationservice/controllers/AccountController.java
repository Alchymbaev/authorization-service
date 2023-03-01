package kg.megacom.authorizationservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.authorizationservice.models.dtos.AccountDto;
import kg.megacom.authorizationservice.models.request.AuthRequest;
import kg.megacom.authorizationservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Аккаунт")
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    private AccountService service;



    @PostMapping("/create")
    @ApiOperation("Создание аккаунта")
    ResponseEntity<?> save(@RequestBody AuthRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск аккаунта по ID")
    AccountDto findById(@RequestParam(required = false) Long id){
        return service.findById(id);
    }

    @GetMapping("/findAll")
    @ApiOperation("Выыод всех аккаунтов")
    List<AccountDto> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    AccountDto delete(@RequestParam(required = false) Long id) {
        return service.delete(id);
    }
}
 


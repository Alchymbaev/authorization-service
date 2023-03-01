package kg.megacom.authorizationservice.controllers;

import io.swagger.annotations.Api;
import kg.megacom.authorizationservice.models.request.AuthRequest;
import kg.megacom.authorizationservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Авторизация")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AccountService service;

    @PostMapping("/sign/in")
    String auth(@RequestBody AuthRequest request){
        return service.auth(request);
    }
}

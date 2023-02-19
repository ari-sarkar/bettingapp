package com.example.bet.controllers;

import com.example.bet.models.LoginModel;
import com.example.bet.repository.LoginRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/v1/login")
public class LoginController {
    private LoginRepository loginRepository;

    @PostMapping("/otp")
    public ResponseEntity<HashMap> createOtp(LoginModel loginModel){
        HashMap resp = new HashMap();
        try{
            loginRepository.save(loginModel);
            resp.put("message","Saved");
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }
        catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }
}

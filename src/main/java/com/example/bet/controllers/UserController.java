package com.example.bet.controllers;

import com.example.bet.models.UserModel;
import com.example.bet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<HashMap> getAll(@RequestParam int page,@RequestParam int size){
        HashMap resp = new HashMap();
        try {
            Page<UserModel> data = userRepository.findAll(PageRequest.of(page, size,Sort.by("walletBalance").descending()));
            resp.put("status", 200);
            resp.put("message", "All users fetched successfully");
            resp.put("users", data.getContent());
            resp.put("totalElements", data.getTotalElements());
            resp.put("pages", data.getTotalPages());
            return ResponseEntity.ok(resp);
        }catch (Exception e) {
            resp.put("status", 400);
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);

        }
}

    @GetMapping("/user-by-no")
    public ResponseEntity<HashMap> getUserByNo(@RequestParam int page,@RequestParam int size,@RequestParam String no){
        HashMap resp = new HashMap();
        try {
            Page<UserModel> data = userRepository.findByPhoneNo(PageRequest.of(page, size),no);
            resp.put("status", 200);
            resp.put("message", "user fetched successfully");
            resp.put("user", data.getContent());
            resp.put("totalElements", data.getTotalElements());
            resp.put("pages", data.getTotalPages());
            return ResponseEntity.ok(resp);
        }catch (Exception e) {
            resp.put("status", 400);
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);

        }
    }

        @CrossOrigin
        @PostMapping("/add")
        public ResponseEntity<HashMap> addUser(@RequestBody UserModel userModel){
                HashMap resp = new HashMap();
                try{
                    userRepository.save(userModel);
                    resp.put("status",200);
                    return ResponseEntity.ok(resp);
                }catch(Exception e){
                    resp.put("status",400);
                    resp.put("message",e.getMessage());
                    return ResponseEntity.badRequest().body(resp);
                }
        }

        @CrossOrigin
        @PostMapping("/update/{id}")
        public ResponseEntity<HashMap> updateUser(@PathVariable String id, @RequestBody UserModel userModel){
        HashMap resp= new HashMap();
        try{
            UserModel userModelToUpdate = userRepository.getReferenceById(id);
            if(!userModel.getName().equals("")){
                userModelToUpdate.setName(userModel.getName());
            }
            if(!userModel.getPhoneNo().equals("")){
                userModelToUpdate.setPhoneNo(userModel.getPhoneNo());
            }
            if(!userModel.getWalletBalance().equals("")){
                userModelToUpdate.setWalletBalance(userModel.getWalletBalance());
            }
            userRepository.save(userModelToUpdate);
            resp.put("status",200);
            resp.put("message","user data updated");
            return ResponseEntity.ok(resp);
        }catch (Exception e){
            resp.put("status",400);
            return ResponseEntity.badRequest().body(resp);
        }
        }

        @GetMapping("/remove/{id}")
        public ResponseEntity<HashMap> deleteUser(@PathVariable String id){
        HashMap resp = new HashMap();
        try{
            userRepository.deleteById(id);
            resp.put("status",200);
            resp.put("message","user deleted successfully");

           return ResponseEntity.ok(resp);

        }catch (Exception e) {
            resp.put("status",400);
            resp.put("message",e.getMessage());
           return ResponseEntity.badRequest().body(resp);
        }
        };

    @CrossOrigin
    @PostMapping("/wallet-functions/{id}/{to_do}/{amount}")
    public ResponseEntity<HashMap> walletFunctions(@PathVariable String id,@PathVariable String to_do,@PathVariable String amount){
        HashMap resp = new HashMap();
        try {
            UserModel data = userRepository.getReferenceById(id);
            int walletBlance = Integer.parseInt(data.getWalletBalance());
            if(to_do.equals("plus")){
                walletBlance= walletBlance + Integer.parseInt(amount);
                if(data.getTotalRechargeAmount() == null){
                    int total = 0 + Integer.parseInt(amount);
                    data.setTotalRechargeAmount(total);
                }else{
                    int total = data.getTotalRechargeAmount() + Integer.parseInt(amount);
                    data.setTotalRechargeAmount(total);
                }
            }else{
                walletBlance= walletBlance - Integer.parseInt(amount);
            }
            data.setWalletBalance(String.valueOf(walletBlance));
            userRepository.save(data);
            resp.put("message","success");
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }
}

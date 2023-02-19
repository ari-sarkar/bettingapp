package com.example.bet.controllers;

import com.example.bet.models.MyBidsModel;
import com.example.bet.models.UserModel;
import com.example.bet.repository.MyBidsRepository;
import com.example.bet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/my-bids")
public class MyBidsController {
    @Autowired
    private MyBidsRepository myBidsRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/set")
    public ResponseEntity<HashMap> setBid(@RequestBody MyBidsModel myBidsModel){
        HashMap resp= new HashMap();
        try{
            myBidsRepository.save(myBidsModel);
            resp.put("message","success");
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<HashMap> getAllBidsByUserId(@RequestParam int page,@RequestParam int size,@RequestParam String user,@RequestParam String gameId){
        HashMap resp = new HashMap();
        try {
            Page<MyBidsModel> data= myBidsRepository.findByUserAndGameId(PageRequest.of(page,size,Sort.by("date").descending()),user,gameId);
            resp.put("data",data.getContent());
            resp.put("total",data.getTotalElements());
            resp.put("pages",data.getTotalPages());
            resp.put("message","success");
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<HashMap> getAllBidsByQuery(@RequestParam int page,@RequestParam int size,@RequestParam String gameId){
        HashMap resp = new HashMap();
        try {
            Page<MyBidsModel> data= myBidsRepository.findAllByGameId(PageRequest.of(page, size, Sort.by("date").descending()),gameId);
//            Optional<UserModel> userData = userRepository.findById(data.stream().findFirst().get().getUser());
//            data.toSet().
            resp.put("data",data.getContent());
            resp.put("totalPages",data.getTotalPages());
            resp.put("totalElements",data.getTotalElements());
            resp.put("message","success");
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/get-all-by-slot")
    public ResponseEntity<HashMap> getAllBidsBySlot(@RequestParam int page,@RequestParam int size,@RequestParam String slot){
        HashMap resp = new HashMap();
        try {
            Page<MyBidsModel> data= myBidsRepository.findByBajiNo(PageRequest.of(page, size, Sort.by("date").descending()),slot);
//            Optional<UserModel> userData = userRepository.findById(data.stream().findFirst().get().getUser());
//            data.toSet().
            resp.put("data",data.getContent());
            resp.put("totalPages",data.getTotalPages());
            resp.put("totalElements",data.getTotalElements());
            resp.put("message","success");
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<HashMap> updateBid(@RequestParam String result,@RequestParam String id){
        HashMap resp= new HashMap();
        try{
            Optional<MyBidsModel> data = myBidsRepository.findById(id);
            data.get().setResult(result);
            myBidsRepository.save(data.get());
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

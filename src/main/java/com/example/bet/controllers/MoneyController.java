package com.example.bet.controllers;

import com.example.bet.models.MoneyModel;
import com.example.bet.repository.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/v1/money")
public class MoneyController {
    @Autowired
    private MoneyRepository moneyRepository;

    @GetMapping("/get")
    public ResponseEntity<HashMap> all(@RequestParam int page, @RequestParam int size){
        HashMap resp = new HashMap();
        try{
            Page<MoneyModel> data = moneyRepository.findAll(PageRequest.of(page, size, Sort.by("amount").descending()));
            resp.put("message","success");
            resp.put("status","200");
            resp.put("data",data.getContent());
            resp.put("pages",data.getTotalPages());
            resp.put("elements",data.getTotalElements());
            return ResponseEntity.ok(resp);
        }
        catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @CrossOrigin
    @PostMapping("/set")
    public ResponseEntity<HashMap> add(@RequestBody MoneyModel moneyModel){
        HashMap resp = new HashMap();
        try{
            moneyRepository.save(moneyModel);
            resp.put("message","success");
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }
        catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<HashMap> update(@RequestBody MoneyModel moneyModel,@RequestParam String id){
        HashMap resp = new HashMap();
        try{
            Optional<MoneyModel> data = moneyRepository.findById(id);
            data.get().setAmount(moneyModel.getAmount());
            data.get().setIsSettled(moneyModel.getIsSettled());
            moneyRepository.save(data.get());
            resp.put("message","success");
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

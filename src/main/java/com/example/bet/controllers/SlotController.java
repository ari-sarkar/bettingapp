package com.example.bet.controllers;

import com.example.bet.models.GameListModel;
import com.example.bet.models.SlotModel;
import com.example.bet.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/slots")
public class SlotController {
    @Autowired
    SlotRepository slotRepository;

    @GetMapping("/get")
    public ResponseEntity<HashMap> get(@RequestParam String id){
        HashMap resp = new HashMap();
        try{
            List<SlotModel> data = slotRepository.findByGameId(Sort.by("name").ascending(),id);
            resp.put("status","200");
            resp.put("data",data);
            return ResponseEntity.ok(resp);
        }
        catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<HashMap> save(@RequestBody SlotModel slotModel){
        HashMap resp = new HashMap();
        try{
            slotRepository.save(slotModel);
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
    public ResponseEntity<HashMap> update(@RequestBody SlotModel slotModel,@RequestParam String id){
        HashMap resp = new HashMap();
        try{
            Optional<SlotModel> data = slotRepository.findById(id);
            data.get().setStatus(slotModel.getStatus());
            slotRepository.save(data.get());
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

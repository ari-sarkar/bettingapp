package com.example.bet.controllers;

import com.example.bet.models.WinningNumbersModel;
import com.example.bet.repository.WinningNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/v1/winnings")
public class WinningModelsController {
    @Autowired
    private WinningNumbersRepository winningNumbersRepository;

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<HashMap> save(@RequestBody WinningNumbersModel winningNumbersModel){
        HashMap resp = new HashMap();
        try{
            winningNumbersRepository.save(winningNumbersModel);
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
    public ResponseEntity<HashMap> update(@RequestBody WinningNumbersModel winningNumbersModel,@RequestParam String id){
        HashMap resp = new HashMap();
        try{
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
//            Date date = formatter.parse(id);
            Optional<WinningNumbersModel> data = winningNumbersRepository.findById(id);
            data.get().setSlot(winningNumbersModel.getSlot());
            data.get().setSingle(winningNumbersModel.getSingle());
            data.get().setTriple(winningNumbersModel.getTriple());
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
    public ResponseEntity<HashMap> findByDate(@RequestParam String id){
        HashMap resp = new HashMap();
        try{
            List<WinningNumbersModel> data = winningNumbersRepository.findByDate(id);
            resp.put("data",data);
            resp.put("status",200);
            resp.put("message","success");

            return ResponseEntity.ok(resp);

        }catch (Exception e) {
            resp.put("status",400);
            resp.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    };

    @GetMapping("/get-by-game")
    public ResponseEntity<HashMap> findByGameId(@RequestParam int page,@RequestParam int size,@RequestParam String id,@RequestParam String date){
        HashMap resp = new HashMap();
        try{
            Page<WinningNumbersModel> data = winningNumbersRepository.findByGameIdAndDate(PageRequest.of(page,size, Sort.by("slot").ascending()),id,date);
            resp.put("data",data.getContent());
            resp.put("status",200);
            resp.put("message","success");

            return ResponseEntity.ok(resp);

        }catch (Exception e) {
            resp.put("status",400);
            resp.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    };
}

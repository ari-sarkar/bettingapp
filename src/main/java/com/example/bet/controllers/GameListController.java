package com.example.bet.controllers;

import com.example.bet.models.GameListModel;
import com.example.bet.repository.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/games")
public class GameListController {

    @Autowired
    private GameListRepository gameListRepository;

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<HashMap> save(@RequestBody GameListModel gameListModel){
        HashMap resp = new HashMap();
        try{
            gameListRepository.save(gameListModel);
            resp.put("status","200");
            return ResponseEntity.ok(resp);
        }
        catch (Exception e){
            resp.put("message",e.getMessage());
            resp.put("status","400");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<HashMap> get(){
        HashMap resp = new HashMap();
        try{
            List<GameListModel> data = gameListRepository.findAll();
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
}

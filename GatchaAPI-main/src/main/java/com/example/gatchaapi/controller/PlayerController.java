package com.example.gatchaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gatchaapi.model.Player;
import com.example.gatchaapi.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;


    @PostMapping("/create")
    public ResponseEntity<Player> createPlayer(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Player player) {
        try {
            // Extract token from Authorization header
            String token = authorizationHeader.replace("Bearer ", "");
            Player createdPlayer = playerService.createPlayer(token, player);
            return ResponseEntity.ok(createdPlayer);
        } catch (Exception e) {
            // Log the exception details
            System.err.println("Error in createPlayer endpoint: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Player> getProfile(@RequestHeader("Authorization") String token) {
        Player player = playerService.getPlayerProfile(token);
        return ResponseEntity.ok(player);
    }

    @GetMapping("/level")
    public ResponseEntity<Integer> getLevel(@RequestHeader("Authorization") String token) {
        int level = playerService.getPlayerLevel(token);
        return ResponseEntity.ok(level);
    }

    @GetMapping("/monsters")
    public ResponseEntity<List<String>> getMonsters(@RequestHeader("Authorization") String token) {
        List<String> monsters = playerService.getPlayerMonsters(token);
        return ResponseEntity.ok(monsters);
    }

    @PostMapping("/gain-experience")
    public ResponseEntity<Player> gainExperience(@RequestHeader("Authorization") String token, @RequestParam int experience) {
        Player player = playerService.gainExperience(token, experience);
        return ResponseEntity.ok(player);
    }

    @PostMapping("/level-up")
    public ResponseEntity<Player> levelUp(@RequestHeader("Authorization") String token) {
        Player player = playerService.levelUp(token);
        return ResponseEntity.ok(player);
    }

    @PostMapping("/add-monster")
    public ResponseEntity<Player> addMonster(@RequestHeader("Authorization") String token, @RequestParam String monsterId) {
        Player player = playerService.addMonster(token, monsterId);
        return ResponseEntity.ok(player);
    }

    @PostMapping("/remove-monster")
    public ResponseEntity<Player> removeMonster(@RequestHeader("Authorization") String token, @RequestParam String monsterId) {
        Player player = playerService.removeMonster(token, monsterId);
        return ResponseEntity.ok(player);
    }
}
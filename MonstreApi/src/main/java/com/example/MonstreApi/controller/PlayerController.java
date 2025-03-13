package com.example.MonstreApi.controller;

import com.example.MonstreApi.model.Player;
import com.example.MonstreApi.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")

public class PlayerController {

    private final PlayerService service;
    public PlayerController(PlayerService service) {this.service = service;}

    @GetMapping("/getPlayer")
    public Player getPlayer(@RequestParam String id) {
        return service.getPlayerById(id);
    }//marche pas encore

    @GetMapping("/test")
    public String test() {
        return "Player API";
    }
}

package com.example.MonstreApi.service;

import com.example.MonstreApi.model.Player;
import com.example.MonstreApi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

     @Autowired
    private PlayerRepository playerRepo;

    public PlayerService(PlayerRepository repo) {
        this.playerRepo = repo;
    }

    public void SavePlayer(Player player) {playerRepo.save(player);}

    public void deletePlayer(String id) {playerRepo.deleteById(id);}

    public Player getPlayerById(String id) {return playerRepo.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));}

    public Player giveXp(String id) {
        Player player =
        getPlayerById(id);
        player.setXp(player.getXp() + 40);
        return playerRepo.save(player);
    }
}

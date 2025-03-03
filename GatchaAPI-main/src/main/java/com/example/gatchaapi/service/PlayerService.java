package com.example.gatchaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gatchaapi.model.Player;
import com.example.gatchaapi.repository.PlayerRepository;
import com.example.gatchaapi.repository.UserRepository;
import com.example.gatchaapi.model.User;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    public Player getPlayerProfile(String token) {
        try {
            User user = getUserByToken(token);
            return playerRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Player not found"));
        } catch (Exception e) {
            // Log the exception details
            System.err.println("Error fetching player profile: " + e.getMessage());
            throw new RuntimeException("Internal Server Error");
        }
    }

    private User getUserByToken(String token) {
        return userRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token"));
    }
    public Player createPlayer(String token, Player player) {
        try {
            // Validate required fields
            if (player.getUsername() == null || player.getUsername().isEmpty()) {
                throw new IllegalArgumentException("Username is required");
            }

            // Extract userId from token
            User user = getUserByToken(token);
            if (user == null) {
                throw new RuntimeException("Invalid token");
            }
            player.setUserId(user.getId());

            // Check for unique username
            Optional<Player> existingPlayer = playerRepository.findByUsername(player.getUsername());
            if (existingPlayer.isPresent()) {
                throw new IllegalArgumentException("Username already exists");
            }

            // Initialize default values if necessary
            if (player.getLevel() == 0) {
                player.setLevel(1);
            }
            if (player.getExperience() == 0) {
                player.setExperience(0);
            }
            if (player.getMonsters() == null) {
                player.setMonsters(new ArrayList<>());
            }

            return playerRepository.save(player);
        } catch (Exception e) {
            // Log the exception details
            System.err.println("Error creating player: " + e.getMessage());
            throw new RuntimeException("Internal Server Error");
        }
    }


    public int getPlayerLevel(String token) {
        Player player = getPlayerProfile(token);
        return player.getLevel();
    }

    public List<String> getPlayerMonsters(String token) {
        Player player = getPlayerProfile(token);
        return player.getMonsters();
    }

    public Player gainExperience(String token, int experience) {
        Player player = getPlayerProfile(token);
        player.setExperience(player.getExperience() + experience);
        return playerRepository.save(player);
    }



    public Player levelUp(String token) {
        Player player = getPlayerProfile(token);
        player.setLevel(player.getLevel() + 1);
        player.setExperience(0);
        return playerRepository.save(player);
    }

    public Player addMonster(String token, String monsterId) {
        Player player = getPlayerProfile(token);
        player.getMonsters().add(monsterId);
        return playerRepository.save(player);
    }

    public Player removeMonster(String token, String monsterId) {
        Player player = getPlayerProfile(token);
        player.getMonsters().remove(monsterId);
        return playerRepository.save(player);
    }
}
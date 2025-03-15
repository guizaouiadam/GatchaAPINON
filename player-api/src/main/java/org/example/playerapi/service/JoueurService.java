package org.example.playerapi.service;

import org.example.playerapi.model.Joueur;
import org.example.playerapi.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Service
public class JoueurService {
    @Autowired
    private JoueurRepository joueurRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String validateToken(String token) {
        System.out.println("Received token: " + token);
        String authUrl = "http://auth-api:8081/api/auth/validate?token=" + token;
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(authUrl, Boolean.class);
            System.out.println("Response: " + response);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody()) {
                return "Valid";
            } else {
                throw new RuntimeException("Invalid or expired token");
            }
        } catch (ResourceAccessException e) {
            System.err.println("Connection refused: " + e.getMessage());
            throw new RuntimeException("Unable to connect to auth service", e);
        } catch (RestClientException e) {
            System.err.println("Error during REST call: " + e.getMessage());
            throw new RuntimeException("Error during REST call", e);
        }
    }
    @PostMapping
    public Optional<Joueur> getJoueur(String id, String token) {
        validateToken(token);
        return joueurRepository.findById(id);
    }

    public Joueur saveJoueur(Joueur joueur, String token) {
        validateToken(token);
        return joueurRepository.save(joueur);
    }

    public void deleteJoueur(String id, String token) {
        validateToken(token);
        joueurRepository.deleteById(id);
    }

    public Joueur gainExperience(String id, int experience, String token) {
        validateToken(token);
        Optional<Joueur> joueurOpt = joueurRepository.findById(id);
        if (joueurOpt.isPresent()) {
            Joueur joueur = joueurOpt.get();
            joueur.setExperience(joueur.getExperience() + experience);
            while (joueur.getExperience() >= getExperienceThreshold(joueur.getLevel())) {
                joueur.setExperience(joueur.getExperience() - getExperienceThreshold(joueur.getLevel()));
                joueur.setLevel(joueur.getLevel() + 1);
                joueur.getMonstres().add(null); // Increase the size of the monster list
            }
            return joueurRepository.save(joueur);
        }
        return null;
    }

    public Joueur addMonstre(String id, String monstreId, String token) {
        validateToken(token);
        Optional<Joueur> joueurOpt = joueurRepository.findById(id);
        if (joueurOpt.isPresent()) {
            Joueur joueur = joueurOpt.get();
            if (joueur.getMonstres().size() < joueur.getLevel() + 10) {
                joueur.getMonstres().add(monstreId);
                return joueurRepository.save(joueur);
            }
        }
        return null;
    }

    public Joueur removeMonstre(String id, String monstreId, String token) {
        validateToken(token);
        Optional<Joueur> joueurOpt = joueurRepository.findById(id);
        if (joueurOpt.isPresent()) {
            Joueur joueur = joueurOpt.get();
            joueur.getMonstres().remove(monstreId);
            return joueurRepository.save(joueur);
        }
        return null;
    }

    private int getExperienceThreshold(int level) {
        return (int) (50 * Math.pow(1.1, level - 1));
    }
}
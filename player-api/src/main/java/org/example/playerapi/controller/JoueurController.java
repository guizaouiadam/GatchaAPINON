package org.example.playerapi.controller;

import org.example.playerapi.model.Joueur;
import org.example.playerapi.service.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/joueur")
public class JoueurController {
    @Autowired
    private JoueurService joueurService;

    @GetMapping("/{id}")
    public ResponseEntity<Joueur> getJoueur(@PathVariable String id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.of(joueurService.getJoueur(id, token));
    }

    @PostMapping("/add")
    public ResponseEntity<Joueur> saveJoueur(@RequestBody Joueur joueur, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(joueurService.saveJoueur(joueur, token)); //ne rien mettre pour userName et id dans le body
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoueur(@PathVariable String id, @RequestHeader("Authorization") String token) {
        joueurService.deleteJoueur(id, token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/gainExperience")
    public ResponseEntity<Joueur> gainExperience(@PathVariable String id, @RequestParam int experience, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(joueurService.gainExperience(id, experience, token));
    }

    @PostMapping("/{id}/addMonstre")
    public ResponseEntity<Joueur> addMonstre(@PathVariable String id, @RequestParam String monstreId, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(joueurService.addMonstre(id, monstreId, token));
    }

    @PostMapping("/{id}/removeMonstre")
    public ResponseEntity<Joueur> removeMonstre(@PathVariable String id, @RequestParam String monstreId, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(joueurService.removeMonstre(id, monstreId, token));
    }
}
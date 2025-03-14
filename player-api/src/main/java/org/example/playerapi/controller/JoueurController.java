package org.example.playerapi.controller;

import org.example.playerapi.model.Joueur;
import org.example.playerapi.service.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/joueur")
public class JoueurController {
    @Autowired
    private JoueurService joueurService;

    @GetMapping("/{id}")
    public Optional<Joueur> getJoueur(@PathVariable String id) {
        return joueurService.getJoueur(id);
    }

    @PostMapping
    public Joueur createJoueur(@RequestBody Joueur joueur) {
        return joueurService.saveJoueur(joueur);
    }

    @PutMapping("/{id}/experience")
    public Joueur gainExperience(@PathVariable String id, @RequestParam int experience) {
        return joueurService.gainExperience(id, experience);
    }

    @PutMapping("/{id}/monstre")
    public Joueur addMonstre(@PathVariable String id, @RequestParam String monstreId) {
        return joueurService.addMonstre(id, monstreId);
    }

    @DeleteMapping("/{id}/monstre")
    public Joueur removeMonstre(@PathVariable String id, @RequestParam String monstreId) {
        return joueurService.removeMonstre(id, monstreId);
    }
}
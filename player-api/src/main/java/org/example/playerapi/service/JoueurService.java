package org.example.playerapi.service;

import org.example.playerapi.model.Joueur;
import org.example.playerapi.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JoueurService {
    @Autowired
    private JoueurRepository joueurRepository;

    public Optional<Joueur> getJoueur(String id) {
        return joueurRepository.findById(id);
    }

    public Joueur saveJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }

    public void deleteJoueur(String id) {
        joueurRepository.deleteById(id);
    }

    public Joueur gainExperience(String id, int experience) {
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

    public Joueur addMonstre(String id, String monstreId) {
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

    public Joueur removeMonstre(String id, String monstreId) {
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
package org.example.playerapi.repository;

import org.example.playerapi.model.Joueur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JoueurRepository extends MongoRepository<Joueur, String> {
}

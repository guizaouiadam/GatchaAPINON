package com.example.mongoTuto.repository;

import com.example.mongoTuto.model.Monster;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MonsterRepository extends MongoRepository<Monster, String> {

    public List<Monster> findByName(String name);
    public List<Monster> findByElement(String element);
}

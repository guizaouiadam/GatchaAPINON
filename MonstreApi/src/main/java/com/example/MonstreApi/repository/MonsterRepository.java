package com.example.MonstreApi.repository;

import com.example.MonstreApi.model.Monster;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MonsterRepository extends MongoRepository<Monster, String> {

    public List<Monster> findByName(String name);
    public List<Monster> findByElement(String element);
}

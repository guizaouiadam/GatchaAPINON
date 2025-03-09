package com.example.mongoTuto.service;
import com.example.mongoTuto.model.Monster;
import com.example.mongoTuto.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository repo;


    public MonsterService(MonsterRepository repo) {
        this.repo = repo;
    }

    public void saveMonster(Monster monster) {
        repo.save(monster);
    }

    public List<Monster> findByName(String name) {
        return repo.findByName(name);
    }

    public List<Monster> findAll() {
        return repo.findAll();
    }

    public List<Monster> findElement(String element) {
        return repo.findByElement(element);
    }


    public Monster getMonsterById(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Monster not found"));
    }

    public Monster updateMonster(String id) {
        Monster monster = getMonsterById(id);
        monster.setLevel(monster.getLevel()+1);
        return repo.save(monster);
    }


}

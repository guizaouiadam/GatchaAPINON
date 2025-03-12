package com.example.MonstreApi.service;
import com.example.MonstreApi.model.Monster;
import com.example.MonstreApi.repository.MonsterRepository;
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

    public Monster updateMonster(String id,int skillIndex) {
        Monster monster = getMonsterById(id);
        leveledUp(monster,skillIndex);
        return repo.save(monster);
    }

    public void leveledUp(Monster monster,int skillIndex) {
            monster.setLevel(monster.getLevel()+1);
            monster.setXp(0);
            monster.setAttack(monster.getAttack()+10);
            monster.setDefense(monster.getDefense()+10);
            monster.setHp(monster.getHp()+10);
            monster.setSpeed(monster.getSpeed()+10);
            monster.getSkills().get(skillIndex).level+=1;

    }
    public Monster giveXp(String id,int skillIndex) {
        Monster monster = getMonsterById(id);
        monster.setXp(monster.getXp()+20);
        if(monster.getXp()>200) {
            leveledUp(monster,skillIndex);
        }
        return repo.save(monster);
    }

    public void deleteMonster(String id) {
        repo.deleteById(id);
    }


}

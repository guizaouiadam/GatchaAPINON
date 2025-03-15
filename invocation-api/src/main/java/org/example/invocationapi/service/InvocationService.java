package org.example.invocationapi.service;

import com.example.gatchaapi.model.Monster;
import com.example.gatchaapi.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class InvocationService {
    @Autowired
    private MonsterRepository monsterRepository;

    public Monster invokeMonster() {
        List<Monster> monsters = monsterRepository.findAll();
        double totalRate = monsters.stream().mapToDouble(Monster::getLootRate).sum();
        double randomValue = new Random().nextDouble() * totalRate;

        double cumulativeRate = 0.0;
        for (Monster monster : monsters) {
            cumulativeRate += monster.getLootRate();
            if (randomValue <= cumulativeRate) {
                return monster;
            }
        }
        return null; // Should not reach here if rates are correctly set
    }
}
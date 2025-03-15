package com.example.gatchaapi.service;
import com.example.gatchaapi.model.Monster;
import com.example.gatchaapi.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    public MonsterService(MonsterRepository repo) {
        this.repo = repo;
    }

    public void saveMonster(Monster monster,String token) {
        validateToken(token);
        repo.save(monster);
    }

    public List<Monster> findByName(String name,String token) {
        validateToken(token);
        return repo.findByName(name);
    }

    public List<Monster> findAll(String token) {
        validateToken(token);
        return repo.findAll();
    }

    public List<Monster> findElement(String element,String token) {
        validateToken(token);
        return repo.findByElement(element);
    }


    public Monster getMonsterById(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Monster not found"));
    }

    public Monster updateMonster(String id,int skillIndex,String token) {
        validateToken(token);
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
    public Monster giveXp(String id,int skillIndex,String token) {
        validateToken(token);
        Monster monster = getMonsterById(id);
        monster.setXp(monster.getXp()+20);
        if(monster.getXp()>200) {
            leveledUp(monster,skillIndex);
        }
        return repo.save(monster);
    }

    public void deleteMonster(String id,String token) {
        validateToken(token);
        repo.deleteById(id);
    }

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

}

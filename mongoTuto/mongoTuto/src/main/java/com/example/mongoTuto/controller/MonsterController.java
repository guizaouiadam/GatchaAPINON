package com.example.mongoTuto.controller;
import com.example.mongoTuto.MonsterDto.Element;
import com.example.mongoTuto.MonsterDto.MonsterDto;
import com.example.mongoTuto.model.Monster;
import org.springframework.web.bind.annotation.*;
import com.example.mongoTuto.service.MonsterService;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/monsters")
public class MonsterController {

   private final MonsterService service;
   public MonsterController(MonsterService service) {this.service = service;}
    @GetMapping("/getLog")
    String log (){
        String log ="Ceci est une requête, code : ";
        log +=401;
        return log;
    }

    @PostMapping("/save")
    public ResponseEntity<String> monsters (@RequestBody MonsterDto monster){
        service.saveMonster(new Monster(
                monster.getName(),
                monster.getId(),
                monster.getAttack(),
                monster.getDefense(),
                monster.getHp(),
                monster.getLootRate(),
                monster.getLevel(),
                monster.getElement().toString())
        );
        return ResponseEntity.ok("monster saved!");
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<MonsterDto>> getmonsters (@PathVariable String name) {
        List<MonsterDto> monstersByName = service.findByName(name)
                .stream()
                .map(monster -> new MonsterDto(monster.getName(),
                        monster.getId(),
                        monster.getAttack(),
                        monster.getDefense(),
                        monster.getHp(),
                        monster.getLootRate(),
                        monster.getLevel(),
                        Element.valueOf(monster.getElement().toString()) )  )
                .toList();
                return ResponseEntity.ok(monstersByName)  ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MonsterDto>> getAllMonsters() {
        List<MonsterDto> allMonsters = service.findAll()
                .stream()
                .map(monster -> new MonsterDto(monster.getName(),
                        monster.getId(),
                        monster.getAttack(),
                        monster.getDefense(),
                        monster.getHp(),
                        monster.getLootRate(),
                        monster.getLevel(),
                        Element.valueOf(monster.getElement().toString()) )  )
                .toList();
        return ResponseEntity.ok(allMonsters);
    }

    @GetMapping("/elements/{element}")
    public ResponseEntity<List<MonsterDto>> getMonstersElement(@PathVariable String element)   {
              List<MonsterDto> elementMonsters = service.findElement(element)
                    .stream()
                    .map(monster -> new MonsterDto(monster.getName(),
                            monster.getId(),
                            monster.getAttack(),
                            monster.getDefense(),
                            monster.getHp(),
                            monster.getLootRate(),
                            monster.getLevel(),
                            Element.valueOf(monster.getElement())  ))
                            .toList();
              return ResponseEntity.ok(elementMonsters);
    }

    @PutMapping("/levelup/id={id}")
    public ResponseEntity<Monster> levelUpMonster (@PathVariable String id) {
        //Monster toLevelUp = service.findById("Dracaufeu").getFirst();
        //int oldLevel = toLevelUp.getLevel();
        Monster updatedMonster = service.updateMonster(id);
        //int newLevel = toLevelUp.getLevel();
        return ResponseEntity.ok(updatedMonster);
    }
}
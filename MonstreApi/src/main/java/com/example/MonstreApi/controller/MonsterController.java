package com.example.MonstreApi.controller;

import com.example.MonstreApi.MonsterDto.Element;
import com.example.MonstreApi.MonsterDto.MonsterDto;
import com.example.MonstreApi.model.Monster;
import com.example.MonstreApi.service.MonsterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                monster.getElement().toString(),
                monster.getSpeed(),
                monster.getXp(),
                monster.getSkills())
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
                        Element.valueOf(monster.getElement().toString()),
                        monster.getSpeed(),
                        monster.getXp(),
                        monster.getSkills())  )
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
                        Element.valueOf(monster.getElement().toString()),
                        monster.getSpeed(),
                        monster.getXp(),
                        monster.getSkills())  )
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
                            Element.valueOf(monster.getElement()),
                            monster.getSpeed(),
                            monster.getXp(),
                            monster.getSkills())  )
                            .toList();
              return ResponseEntity.ok(elementMonsters);
    }

    @PutMapping("/levelup/id={id}/skill={skillIndex}")
    public ResponseEntity<Monster> levelUpMonster (@PathVariable String id,@PathVariable int skillIndex) {
        //Monster toLevelUp = service.findById("Dracaufeu").getFirst();
        //int oldLevel = toLevelUp.getLevel();
        Monster updatedMonster = service.updateMonster(id,skillIndex);
        //int newLevel = toLevelUp.getLevel();
        return ResponseEntity.ok(updatedMonster);
    }

    @PutMapping("/giveXp/id={id}/skill={skillIndex}")
    public ResponseEntity<Monster> giveXp (@PathVariable String id,@PathVariable int skillIndex) {
       Monster updatedMonster = service.giveXp(id,skillIndex);
       return ResponseEntity.ok(updatedMonster);
    }

    @DeleteMapping("/delete/id={id}")
    public ResponseEntity<Void> deleteMonster(@PathVariable String id){
       service.deleteMonster(id);
       return ResponseEntity.noContent().build();
    }
}
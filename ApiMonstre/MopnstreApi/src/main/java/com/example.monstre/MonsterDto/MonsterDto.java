package com.example.monstre.MonsterDto;

import com.example.mongoTuto.model.Skill;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class MonsterDto {

    private String name;
    private String id;
    private int attack;
    private int defense;
    private int hp;
    private double lootRate;
    private int xp;
    private int level;
    private List<Skill> skills;
    private int speed;
    private Element element;

    public String getName(){
        return name;
    }
    public int getLevel(){
        return level;
    }
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }
    public int getHp(){
        return hp;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public int getXp(){return xp;}
    public int getSpeed(){ return speed;}
    public double getLootRate(){
        return lootRate;
    }
    public Element getElement(){
        return element;
    }

    public String getId(){
        return id;
    }

    public MonsterDto(String name,String id,int attack,int defense,int hp, double lootRate,int level,Element element,int speed,int xp,List<Skill> skills ){
        this.name = name;
        this.attack= attack;
        this.defense = defense;
        this.hp = hp;
        this.lootRate = lootRate;
        this.level = level;
        this.element = element;
        this.id = id;
        this.speed = speed;
        this.xp=xp;
        this.skills = skills;
    }
}

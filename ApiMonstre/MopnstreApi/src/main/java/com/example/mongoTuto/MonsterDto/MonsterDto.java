package com.example.mongoTuto.MonsterDto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


public class MonsterDto {

    private String name;
    private String id;
    private int attack;
    private int defense;
    private int hp;
    private double lootRate;
    private int level;
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
    public double getLootRate(){
        return lootRate;
    }
    public Element getElement(){
        return element;
    }

    public String getId(){
        return id;
    }

    public MonsterDto(String name,String id,int attack,int defense,int hp, double lootRate,int level,Element element) {
        this.name = name;
        this.attack= attack;
        this.defense = defense;
        this.hp = hp;
        this.lootRate = lootRate;
        this.level = level;
        this.element = element;
        this.id = id;
    }
}

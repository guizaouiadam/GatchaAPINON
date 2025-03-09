package com.example.mongoTuto.model;

import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.annotation.Id;

//Model
@Setter
@Getter
public class Monster {
    private String element;
    private String name;
    private int attack;
    private int defense;
    private int hp;
    private double lootRate;
    private int level;
    private String id;

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

    public String getElement() {
        return element;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }


    public Monster(String name,String id,int attack, int defense, int hp, double lootRate, int level,String element) {
        this.name = name;
        this.attack= attack;
        this.defense = defense;
        this.hp = hp;
        this.lootRate = lootRate;
        this.level = level;
        this.element = element;
        this.id=id;
    }

    public void levelUp(){
        this.level++;
        this.attack+=10;
    }

    @Override
    public String toString() {
        return "Monster {"+
                "Id ="+id+
                "name="+ name+
                "element="+ element+
                "level = "+level+
                "attack ="+attack+
                "defense = "+defense+
                "hp = "+hp+
                "lootRate = "+lootRate+
                "}";
    }
}

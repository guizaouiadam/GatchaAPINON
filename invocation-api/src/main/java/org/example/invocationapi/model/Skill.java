package org.example.invocationapi.model;

public class Skill {
    public String name;
    public int damage;
    public double damageRatio;
    public int cooldown;
    public int level;
    static int  levelMax;


    @Override

    public String toString() {
        return"Skill {"+
                "name="+name+
                "damage="+damage+
                "damageRatio="+damageRatio+
                "coolDown="+cooldown+
                "level="+level+
                "levelMax="+levelMax;

    }
}



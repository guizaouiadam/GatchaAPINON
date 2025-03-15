package org.example.playerapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "joueurs")
public class Joueur {

    private String id;
    private String userName ;
    private int level;
    private int experience;
    private List<String> monstres;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<String> getMonstres() {
        return monstres;
    }

    public void setMonstres(List<String> monstres) {
        this.monstres = monstres;
    }

    // Getters and Setters

    public String toString() {
        return  "User {"+
                "id:"+id+
                "name:"+userName+
                "level:"+level+
                "experience:"+experience+
                "monstres:"+monstres+"}";
    }
}
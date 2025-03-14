package com.example.MonstreApi.model;



import java.util.List;

//add import of user data from auth API

public class Player {

   private String id;
   private int level;
   private List<Monster> monsters;
   private int xp;

   public int getXp() {
      return xp;
   }
   public void setXp(int xp) {this.xp = xp;}

   public String getId() {return id;}

   public Player(String id, int level, List<Monster> monsters, int xp) {
      this.id=id;
      this.level=level;
      this.monsters=monsters;
      this.xp=xp;
   }

   @Override
   public String toString() {
      return "player{"+
              "id:"+id+
              "level"+level+
              "monsters:"+monsters+
              "xp:"+xp+
              "}";
   }
}

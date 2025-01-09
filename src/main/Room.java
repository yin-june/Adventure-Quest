package main;

import entity.*; 
import java.awt.image.BufferedImage;

public class Room {
    private Monster[] monsters; 
    private Item item; 
    private Hero hero;
    private BufferedImage backgroundImage;
    
    public Room(Monster[] monsters, Item item, BufferedImage backgroundImage){
        this.monsters = monsters; 
        this.item = item; 
        this.backgroundImage = backgroundImage;
    }
    
    public Monster[] getMonsters(){
        return monsters; 
    }

    public void removeMonster(Monster monster) {
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] == monster) {
                monsters[i] = null;
                break;
            }
        }
    }

    public void setHero(Hero hero){
        this.hero = hero; 
    }   

    public Hero getHero(){
        return hero; 
    }

    public void setItem(Item item){
        this.item = item; 
    }

    public Item getItem(){
        return item; 
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }


    public void updateMonsters(long currentTime) {
        for (Monster monster : monsters) {
            if (monster != null) {
                monster.update(currentTime);
            }
        }
    }
}

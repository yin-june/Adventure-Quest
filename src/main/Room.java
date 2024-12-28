package main;

import entity.*; 
import java.awt.image.BufferedImage;

public class Room {
    private Monster monster; 
    private Item item; 
    private Hero hero;
    private BufferedImage backgroundImage;
    
    public Room(Monster monster, Item item, BufferedImage backgroundImage){
        this.monster = monster; 
        this.item = item; 
        this.backgroundImage = backgroundImage;
    }

    public void setMonster(Monster monster){
        this.monster = monster; 
    }
    
    public Monster getMonster(){
        return monster; 
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
}

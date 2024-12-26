package main;

import entity.*; 
public class Room {
    private Monster monster; 
    private Item item; 
    
    public Room(Monster monster, Item item){
        this.monster = monster; 
        this.item = item; 
    }
    
    public Monster getMonster(){
        return monster; 
    }
    
    public Item getItem(){
        return item; 
    }
}

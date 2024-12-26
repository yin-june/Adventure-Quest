package main;

import entity.*;
import java.io.IOException;
import java.util.Random;

public class Dungeon {
    private Room[] rooms;
    private int currentRoom;

    public Dungeon(int size) {
        rooms = new Room[size];
        generateDungeon();
        currentRoom = 0; // Start at the first room
    }

    private void generateDungeon() {
        Random rand = new Random();
        for (int i = 0; i < rooms.length; i++) {
            Monster monster = generateMonster();
            monster.setPosition(rand.nextInt(GamePanel.SCREEN_WIDTH - 50), rand.nextInt(GamePanel.SCREEN_HEIGHT -50 ));
            monster.setDirection(rand.nextBoolean() ? "left" : "right");

            Item item = generateItem();

            rooms[i] = new Room(monster, item);
            
        }
    }

    private Monster generateMonster(){
        Random r =new Random(); 
        int monsterType = r.nextInt(3); 
        switch(monsterType){
            case 0: 
                return new Goblin();
            case 1: 
                return new Skeleton(); 
            case 2: 
                return new Zombie(); 
            default:
                return new Goblin(); //default to goblin if something goes wrong 
                
        }
    }
    
    private Item generateItem() {
        Random rand = new Random();
        String[] itemNames = {"axe", "dagger", "potion"};
        String itemName = itemNames[rand.nextInt(itemNames.length)];
        try {
            return new Item(null, itemName);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if item creation fails
        }
    }
    
    public Room getCurrentRoom() {
        return rooms[currentRoom];
    }

    public void moveNorth() {
        if (currentRoom < rooms.length - 1) {
            currentRoom++;
        }
    }

    public void moveSouth() {
        if (currentRoom > 0) {
            currentRoom--;
        }
    }

    public int getCurrentRoomIndex() {
        return currentRoom;
    }
    
}

package main;

import entity.*;
import java.util.Random;

public class Dungeon {
    private Monster[] rooms;
    private int currentRoom;

    public Dungeon(int size) {
        rooms = new Monster[size];
        generateDungeon();
        currentRoom = 0; // Start at the first room
    }

    private void generateDungeon() {
        Random rand = new Random();
        for (int i = 0; i < rooms.length; i++) {
            int encounter = rand.nextInt(3);
            switch (encounter) {
                case 0:
                    System.out.println("Empty room");
                    rooms[i] = null; //empty room
                    break;
                case 1:
                    System.out.println("Monster encounter");
                    Monster monster = generateMonster();
                    monster.setPosition(rand.nextInt(GamePanel.SCREEN_WIDTH), rand.nextInt(GamePanel.SCREEN_HEIGHT));
                    monster.setDirection(rand.nextBoolean() ? "left" : "right");
                    rooms[i] = monster;
                    break;
                case 2:
                    System.out.println("Item room");
                    rooms[i] = null; //item room 
                    break;
            }
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
    
    public Monster getCurrentRoomMonster() {
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

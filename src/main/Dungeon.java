package main;

import entity.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;

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
            BufferedImage backgroundImage = generateBackgroundImage();

            rooms[i] = new Room(monster, item, backgroundImage);
            
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

    public BufferedImage generateBackgroundImage(){
        Random rand = new Random();
        String[] backgroundImages = {"src/image/floor2.jpeg", "src/image/floor3.jpeg", "src/image/floor4.jpeg","src/image/floor5.jpeg"};
        String backgroundImagePath = backgroundImages[rand.nextInt(backgroundImages.length)];
        try {
            return ImageIO.read(new File(backgroundImagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if image loading fails
        }

    }
    
    public Room getCurrentRoom() {
        return rooms[currentRoom];
    }

    public void nextRoom() {
        if (currentRoom < rooms.length - 1) {
            currentRoom++;
        }
    }

    public void previousRoom() {
        if (currentRoom > 0) {
            currentRoom--;
        }
    }

    public int getCurrentRoomIndex() {
        return currentRoom;
    }

    public void updateMonster(long currentTime) {
        for (Room room : rooms) {
            if (room != null && room.getMonster() != null) {
                room.getMonster().move();
                room.getMonster().updateDirection(currentTime);
            }
        }
    }
    
    
}

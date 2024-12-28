package main;

import entity.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;

public class Dungeon {
    private Room[] rooms;
    private int currentRoom;
    private String difficulty;

    public Dungeon(int size, String difficulty) {
        this.difficulty = difficulty;
        rooms = new Room[size];
        generateDungeon();
        currentRoom = 0; // Start at the first room
    }

    private void generateDungeon() {
        Random rand = new Random();
        for (int i = 0; i < rooms.length; i++) {
            Monster[] monsters = generateMonsters();
            for (Monster monster : monsters) {
                monster.setPosition(rand.nextInt(48, GamePanel.SCREEN_WIDTH - 50), rand.nextInt(GamePanel.SCREEN_HEIGHT - 50));
                monster.setDirection(rand.nextBoolean() ? "left" : "right");
            }

            Item item = generateItem();
            BufferedImage backgroundImage = generateBackgroundImage();

            rooms[i] = new Room(monsters, item, backgroundImage);
            
        }
    }

    private Monster[] generateMonsters() {
        int num;
        int hp; //added hp 
        int attackPower; // added attackPower

        switch (difficulty) {
            case "Easy":
                num = 1;
                hp = -20;
                attackPower = -10;
                break;
            case "Medium":
                num = 2;
                hp = 0;
                attackPower = 0;
                break;
            case "Difficult":
                num = 3;
                hp = 30;
                attackPower = 20;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }

        Monster[] monsters = new Monster[num];
        for (int i = 0; i < num; i++) {
            monsters[i] = generateMonster(hp, attackPower);
        }
        return monsters;
    }

    private Monster generateMonster(int hp, int attackPower){
        Random r =new Random(); 
        int monsterType = r.nextInt(3); 
        switch(monsterType){
            case 0: 
                return new Goblin(hp, attackPower);
            case 1: 
                return new Skeleton(hp, attackPower); 
            case 2: 
                return new Zombie(hp, attackPower); 
            default:
                throw new IllegalArgumentException("Invalid monster type");
                
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

    // update the monsters in the current room
    public void updateMonster(long currentTime) {
        Room currentRoom = getCurrentRoom();
        if (currentRoom != null) {
            currentRoom.updateMonsters(currentTime);
        }
    }
    
    
}

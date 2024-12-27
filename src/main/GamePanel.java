package main;

import entity.*; 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS 
    public static final int ORIGINAL_TILE_SIZE = 16; // 16 x 16 tiles 
    public static final int SCALE = 3; 
    
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48
    public static final int MAX_SCREEN_COL = 16; 
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels
    //FPS 
    int FPS = 60; 
    
    KeyHandler keyH = new KeyHandler(); 
    Thread gameThread; 
    Hero player; 
    Dungeon dungeon;
    JLabel roomLabel;
    Item item; 
    Monster monster;
    BufferedImage background; 
    
    public GamePanel(String name, int hp, int attackPower, String heroType) throws IOException{
        InventoryPanel inventoryPanel = new InventoryPanel();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow(); //request focus when it is created and when room changes 

        dungeon = new Dungeon(3);

        // Initialize player based on heroType
        switch (heroType) {
            case "Mage":
                player = new Mage(this, keyH, name, attackPower, hp);
                break;
            case "Warrior":
                player = new Warrior(this, keyH, name, attackPower, hp);
                break;
            case "Archer":
                player = new Archer(this, keyH, name, attackPower, hp);
                break;
            default:
                throw new IllegalArgumentException("Invalid hero type: " + heroType);
        }

        //item = new Item(this,"dagger"); 
    }

    public void startGameThread(){
        gameThread = new Thread(this); 
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = Math.pow(10,9)/FPS; // 0.01666sec 
        double delta = 0;
        long lastTime = System.nanoTime(); 
        long currentTime; 
        long timer = 0; 
        int drawCount = 0; 
        
        // game loop
        while(gameThread != null){
            //System.out.println("loop is running");
            currentTime = System.nanoTime(); 
            delta += (currentTime - lastTime)/ drawInterval; 
            timer += (currentTime - lastTime); 
            lastTime = currentTime; 
            
            if(delta >= 1){
                // update infomation 
                update(currentTime);
                // draw the screen with updated information 
                repaint(); // call paint component 
                delta--; 
                drawCount++; 
            }
            if(timer >= Math.pow(10,9)){
                //System.out.println("FPS: " + drawCount);
                drawCount = 0; 
                timer = 0; 
            }
        }
    }
    
    public void update(long currentTime) {
        player.update();
        // Update monsters every frame
        dungeon.updateMonster(currentTime);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2= (Graphics2D)g;
        
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom != null) {
            BufferedImage backgroundImage = currentRoom.getBackgroundImage();
            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            }

            Monster currentMonster = currentRoom.getMonster();
            if (currentMonster != null) {
                currentMonster.draw(g2); // Draw the monster at its position
            }

            Item currentItem = currentRoom.getItem();
            if (currentItem != null) {
                currentItem.draw(g2); // Draw the item at its position
            }
        }
        
        player.draw(g2);
        
        g2.dispose();
        
    }
    
    public Hero getPlayer(){
        return player;
    }

    public void nextRoom() {
        dungeon.nextRoom();
        this.requestFocusInWindow(); //request focus when room changes
        repaint();
    }

    public void previousRoom() {
        dungeon.previousRoom();
        this.requestFocusInWindow(); //request focus when room changes
        repaint();
    }

    public int getCurrentRoomIndex() {
        return dungeon.getCurrentRoomIndex();
    }

    public Monster checkMonsterCollision(Hero hero) {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom != null) {
            Monster monster = currentRoom.getMonster();
            if (monster != null && hero.getBounds().intersects(monster.getBounds())) {
                return monster;
            }
        }
        return null;
    }

    public void removeMonster(Monster monster) {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom != null) {
            currentRoom.setMonster(null);
        }
    }

    public void endGame() {
        JOptionPane.showMessageDialog(this, "Game Over!");
        System.exit(0);
    }
 }

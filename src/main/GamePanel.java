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
    String difficulty;
    Item item;
    Monster monster;
    BufferedImage background;
    JTextArea infoArea;
    InventoryPanel inventoryPanel;
    Item currentItem;
    private GameStatus gameStatus;
    private int score = 0; // Track player score

    public GamePanel(String name, int hp, int attackPower, String heroType, String difficulty) throws IOException{
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow(); //request focus when it is created and when room changes
        this.difficulty = difficulty;

        dungeon = new Dungeon(3, difficulty);
        // initialize inventory panel
        inventoryPanel = new InventoryPanel();

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

        infoArea = new JTextArea(player.displayStats());
        infoArea.setEditable(false);

        // Initialize GameStatus
        gameStatus = new GameStatus((JFrame) SwingUtilities.getWindowAncestor(this), player);
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
                update(currentTime);
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= Math.pow(10,9)){
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(long currentTime) {
        player.update();
        updateStatsDisplay();

        dungeon.updateMonster(currentTime);

        if (!player.isAlive()) {
            gameStatus.showGameOver(score);
        }

        if (allMonstersDefeated(dungeon)) {
            gameStatus.showVictory(score);
        }
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

            Monster[] currentMonsters = currentRoom.getMonsters();
            if (currentMonsters != null) {
                for (Monster monster : currentMonsters) {
                    if (monster != null) {
                        monster.draw(g2);
                    }
                }
            }

            currentItem = currentRoom.getItem();
            if (currentItem != null) {
                currentItem.draw(g2);

                if (player.getBounds().intersects(new Rectangle(currentItem.getX(), currentItem.getY(), 32, 32))) {
                    if (currentRoom.getItem() != null) {
                        inventoryPanel.addItemToInventory(currentItem.getItemImage(),currentItem.getName());
                        currentRoom.setItem(null);
                        score += 10;
                    }
                }
            }
        }
        player.draw(g2);

        g2.dispose();
    }

    public InventoryPanel getInventoryPanel(){
        return inventoryPanel;
    }

    public Hero getPlayer(){
        return player;
    }

    public String getDifficulty() {
        return difficulty;
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
            Monster[] monsters = currentRoom.getMonsters();
            for (Monster monster : monsters) {
                if (monster != null && hero.getBounds().intersects(monster.getBounds())) {
                    return monster;
                }
            }
        }
        return null;
    }

    public void removeMonster(Monster monster) {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom != null) {
            currentRoom.removeMonster(monster);

            // Increase score for defeating a monster
            score += 50;
        }
        this.requestFocusInWindow(); //request focus when monster is defeated
        this.keyH.resetKeys(); // reset key press state
    }

    // Method to check if all monsters in the dungeon are defeated
    private boolean allMonstersDefeated(Dungeon dungeon) {
        for (Room room : dungeon.getRooms()) {
            for (Monster monster : room.getMonsters()) {
                if (monster != null) {
                    return false; // If any room has monsters, return false
                }
            }
        }
        return true; 
    }

    public void updateHeroPosition(Hero player) {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom != null) {
            currentRoom.setHero(player);
        }
        this.requestFocusInWindow(); //request focus when hero moves
        this.keyH.resetKeys(); // reset key press state
    }

    public void updateStatsDisplay() {
        infoArea.setText(player.displayStats());
    }

    public void endGame() {
        gameStatus.showGameOver(score);
    }

    public Item getCurrentItem(){
        return currentItem;
    }

}


package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage; 
import java.util.Random;
import main.GamePanel;

public abstract class Monster extends entity {
    String type;
    int hp;
    int attackPower;
    private long lastDirectionUpdateTime = 0; //time variable for direction update 

    // default values 
    public Monster(String type, int hp, int attackPower) {
        this.type = type;
        this.hp = hp;
        this.attackPower = attackPower;
        this.direction = "right";
        this.speed = 3; 
    }

    public void takeDamage(int damage) {
        if(this.hp - damage > 0){
            this.hp -= damage; 
        }else 
            this.hp = 0; 
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void attack(Hero player) {
        player.takeDamage(this.attackPower);
    }
    
    public String getType(){
        return type;
    }
    
    public int getHp(){
        return hp; 
    }
    
    public void setHp(int hp){
        this.hp = hp; 
    }
    
    public int getAttackPower(){
        return attackPower; 
    }
    
    public void setAttackPower(int attackPower){
        this.attackPower = attackPower; 
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public abstract void getMonsterImage();

    // movement logic
    public void move() {
        switch (direction) {
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
        }

        // Ensure the monster stays within the screen bounds
        if (x < 0) 
            x = 0;
        if (x > GamePanel.SCREEN_WIDTH - 48) 
            x = GamePanel.SCREEN_WIDTH - 48;
        if (y < 0) 
            y = 0;
        if (y > GamePanel.SCREEN_HEIGHT - 48) 
            y = GamePanel.SCREEN_HEIGHT - 48;
    }

    // Update direction every 1 second
    public void updateDirection(long currentTime) {
        if (currentTime - lastDirectionUpdateTime >= Math.pow(10, 9)) {
            Random rand = new Random();
            int moveX = rand.nextInt(3) - 1; // -1, 0, or 1
            int moveY = rand.nextInt(3) - 1; // -1, 0, or 1

            if (moveX < 0) {
                direction = "left";
            } else if (moveX > 0) {
                direction = "right";
            } else if (moveY < 0) {
                direction = "up";
            } else if (moveY > 0) {
                direction = "down";
            }

            lastDirectionUpdateTime = currentTime;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "left":
                image = leftImage;
                break;
            case "right":
                image = rightImage;
                break;
            case "up":
                image = leftImage;
                break;
            case "down":
                image = rightImage;
                break;
        }
        g2.drawImage(image, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, GamePanel.TILE_SIZE/2, GamePanel.TILE_SIZE/2);
    }

    public void update(long currentTime) {
        updateDirection(currentTime);
        move();
    }
    
}

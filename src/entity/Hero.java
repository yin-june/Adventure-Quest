package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.*; 

public abstract class Hero extends entity {
    GamePanel gp;
    KeyHandler keyH;
    private String name;
    private int attackPower;
    private int hp;
    private String heroType;
    //BufferedImage upImage, downImage, leftImage, rightImage;

    public Hero(GamePanel gp, KeyHandler keyH, String name, int attackPower, int hp, String heroType) {
        this.gp = gp;
        this.keyH = keyH;
        this.name = name;
        this.attackPower = attackPower;
        this.hp = hp;
        this.heroType = heroType;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    
    public abstract void getPlayerImage();

    public void update() {
        if (keyH.upPressed && y > 0) {
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed && y < (576 - 50)) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed && x > 0) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed && x<(768 - 40)) {
            direction = "right";
            x += speed;
        }
    }
     public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = upImage;
                break;
            case "down":
                image = downImage;
                break;
            case "left":
                image = leftImage;
                break;
            case "right":
                image = rightImage;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getHeroType() {
        return heroType;
    }

    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }
    
    public void attack(Hero target) {
        target.takeDamage(this.attackPower);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void displayStats() {
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + this.heroType);
        System.out.println("HP: " + this.hp);
        System.out.println("Attack Power: " + this.attackPower);
    }

}
